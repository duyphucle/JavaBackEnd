package com.codegym.cms.controller;

import com.codegym.cms.model.*;
import com.codegym.cms.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.LocalDate;
import java.util.*;

@CrossOrigin(origins = "*")
@RestController
public class PhuctranController {

    @Autowired
    private CauHoiService cauhoiService;

    @Autowired
    private XeService xeService;

    @ModelAttribute("hangxes")
    public Iterable<String> hangXes() {
        return xeService.hangXe2();
    }

    @Autowired
    private DapAnService dapanService;

    @Autowired
    private TraLoiService traloiService;

    @Autowired
    private UserDoingSurveyService userdoingsurveyService;

    @Autowired
    private TraLoiChiTietService traloichitietService;

    @Autowired
    private UserService userService;

    @Autowired
    private NgayService ngayService;

    @ModelAttribute("ngays")
    public Iterable<Ngay> ngays() {
        return ngayService.findAllByIsDeletedEquals(0);
    }

    @Autowired
    private ThangService thangService;

    @ModelAttribute("thangs")
    public Iterable<Thang> thangs() {
        return thangService.findAllByIsDeletedEquals(0);
    }

    @GetMapping(value = "/cauhoi/{surveyId}/{site}", produces = "application/json;charset=UTF-8")
    public ModelAndView CauHoi(@PathVariable("site") Long site, @PathVariable("surveyId") Long surveyId, Principal principal, HttpSession session) {
        Long idUser = userService.idUser(principal.getName());

        UserDoingSurvey userDoingSurvey = userdoingsurveyService.showUserDoingSurvey(surveyId, idUser);
        if (userDoingSurvey == null) {
            userdoingsurveyService.create(idUser, surveyId, LocalDate.now(), principal.getName());

        }
        return getSite(site, surveyId, session);
    }

    @PostMapping(value = "/cauhoi/{surveyId}/{site}", produces = "application/json;charset=UTF-8")
    public ModelAndView create(@ModelAttribute("traloichitiet") TraLoiChiTiet traloichitiet, @RequestParam String idDapan, @RequestParam String idCauhoi, @PathVariable("site") Long site, @PathVariable("surveyId") Long surveyId, HttpSession session, Principal principal) {
        List<String> cauhoi_ids = Arrays.asList(idCauhoi.split(","));
        List<String> dapan_ids = Arrays.asList(idDapan.split(","));
        List<String> values = Arrays.asList(traloichitiet.getValue().split(","));

        Long idUserDoingSurvey;
        Long idUser = userService.idUser(principal.getName());
        for (String cauhoi_id : cauhoi_ids) {
            idUserDoingSurvey = userdoingsurveyService.showIdUserDoingSurvey(Long.valueOf(cauhoi_id), idUser);
            traloiService.create(idUserDoingSurvey, Long.valueOf(cauhoi_id), LocalDate.now(), principal.getName());


        }

        List<String> siteSession = new ArrayList<>();
        Long idTraloi;
        for (int i = 0; i < dapan_ids.size(); i++) {
            idTraloi = traloiService.idTraloi(Long.valueOf(dapan_ids.get(i)));
            traloichitietService.create(idTraloi, Long.valueOf(dapan_ids.get(i)), traloichitiet.getName(), traloichitiet.getLuachon(), values.get(i), traloichitiet.getInnerText(), LocalDate.now(), "Dan");

            List<Long> cauHoiSessions = cauhoiService.listIdCHSession();
            Long idCauHoiByDapAn = dapanService.findById(Long.valueOf(dapan_ids.get(i))).getCauHoi().getId();

            if (cauHoiSessions.contains(idCauHoiByDapAn)) {
                siteSession.add(values.get(i));
                session.setAttribute("cau" + idCauHoiByDapAn, siteSession);
            }

        }

        Long maxSite = cauhoiService.maxSite();

        if (site > maxSite)
            return new ModelAndView("/error-404");
        else
            return getSite(site, surveyId, session);

    }

    private ModelAndView getSite(@PathVariable("site") Long site, @PathVariable("surveyId") Long surveyId, HttpSession session) {
        Iterable<CauHoi> cauhois = cauhoiService.show1(surveyId, site);
        List<DapAn> dapAnss = new ArrayList<>();
        ModelAndView modelAndView = new ModelAndView("/phuctran/phuctran1");

        for (CauHoi cauhoi : cauhois) {
            String[] loai = cauhoi.getLoai().getTenLoai().split(" ");
            if (loai.length > 1) {
                String cauHoiSession = loai[1];
                System.out.println("cauHoiSession  " + loai[1]);
                List<String> dapAnSession = (List<String>) session.getAttribute("cau" + cauHoiSession);
                System.out.println(dapAnSession);

                List<Long> iDCHByLoais = cauhoiService.iDCHByLoai(cauhoi.getLoai().getId());
                System.out.println("iDCHByLoais  " + iDCHByLoais);
                System.out.println("idcauhoi  " + cauhoi.getId());
                System.out.println(iDCHByLoais.contains(cauhoi.getId()));
                System.out.println("----");
                if (iDCHByLoais.contains(cauhoi.getId())) {
                    System.out.println("dapAnSession " + dapAnSession);
                    int index = iDCHByLoais.indexOf(cauhoi.getId());
                    System.out.println("index  " + index);

                    List<String> randomSeries;
                    if (index == 0) {
                        Collections.shuffle(dapAnSession);
                        System.out.println("dapAnSession  shuffle" + dapAnSession);
                        int randomSeriesLength = iDCHByLoais.size();
                        randomSeries = dapAnSession.subList(0, randomSeriesLength);
                        System.out.println("randomSeries  " + randomSeries);
                        System.out.println("=====");
                        session.setAttribute("randomSeries", randomSeries);

                        Iterable<String> dongXes = xeService.dongXe(randomSeries.get(0));
                        System.out.println("randomSeries  " + randomSeries);
                        System.out.println("+++++");
                        modelAndView.addObject("dongXes", dongXes);
                        modelAndView.addObject("cauHoiSession", cauHoiSession);
                    } else {
                        randomSeries = (List<String>) session.getAttribute("randomSeries");
                        System.out.println(session.getAttribute("randomSeries"));
                        System.out.println(index);
                        System.out.println(randomSeries.get(index));
                        Iterable<String> dongXes = xeService.dongXe(randomSeries.get(index));
                        System.out.println("randomSeries  " + randomSeries);
                        System.out.println("+++++");
                        modelAndView.addObject("dongXes", dongXes);
                        modelAndView.addObject("cauHoiSession", cauHoiSession);
                    }
                }
            }

            Iterable<DapAn> dapAns = cauhoiService.showda(cauhoi);
            for (DapAn d : dapAns) {
                dapAnss.add(d);
            }
        }

        if (dapAnss.size() > 0) {
            modelAndView.addObject("cauhois", cauhois);
            modelAndView.addObject("dapAnss", dapAnss);
            modelAndView.addObject("traloichitiet", new TraLoiChiTiet());
            modelAndView.addObject("surveyId", surveyId);
            modelAndView.addObject("site", site);
            return modelAndView;

        } else {
            return new ModelAndView("/error-404");
        }
    }

    @RequestMapping(value = "/hangxe/", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<Iterable<String>> listHangXe() {
        Iterable<String> hangXes = xeService.hangXe();
        if (hangXes == null) {
            return new ResponseEntity<Iterable<String>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Iterable<String>>(hangXes, HttpStatus.OK);
    }

    @RequestMapping(value = "/hangxe/{dongxe}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<Iterable<String>> listHangXe(@PathVariable("dongxe") String dongxe) {
        Iterable<String> dongXes = xeService.dongXe(dongxe);
        if (dongXes == null) {
            return new ResponseEntity<Iterable<String>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Iterable<String>>(dongXes, HttpStatus.OK);
    }

    @GetMapping(value = "/test", produces = "application/json;charset=UTF-8")
    public ModelAndView cart(HttpSession session) {
        List<Long> cauHoiSessions = cauhoiService.listIdCHSession();
        ModelAndView modelAndView = new ModelAndView("/test");

        for (Long cauHoiSession : cauHoiSessions) {
            modelAndView.addObject("cau" + cauHoiSession, session.getAttribute("cau" + cauHoiSession));
        }
        return modelAndView;
    }

    @GetMapping(value = "/home", produces = "application/json;charset=UTF-8")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("/home/home");
        return modelAndView;
    }

    @GetMapping(value = "/survey", produces = "application/json;charset=UTF-8")
    public ModelAndView survey() {
        ModelAndView modelAndView = new ModelAndView("/home/survey");
        return modelAndView;
    }
}
