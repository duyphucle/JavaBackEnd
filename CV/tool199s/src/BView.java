import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

public class BView {

    public void showview() throws SQLException, ClassNotFoundException {
        DTP dtp = new DTP();
        dtp.setFullDTP();
        for (int i = 0; i < dtp.tableSize(); i++) {
            Path path = Paths.get(AMain.link1 + dtp.table(i).toLowerCase() + "/view.html");
            String question = "<!DOCTYPE html>\n" +
                    "<html xmlns:th=\"http://www.thymeleaf.org\">\n" +
                    "\n" +
                    "<head th:replace=\"/layout::head\"></head>\n" +
                    "\n" +
                    "<body id=\"page-top\">\n" +
                    "\n" +
                    "<!-- Page Wrapper -->\n" +
                    "<div id=\"wrapper\">\n" +
                    "\n" +
                    "    <!-- Sidebar -->\n" +
                    "    <th:block th:replace=\"/layout::sidebar\"></th:block>\n" +
                    "    <!-- End of Sidebar -->\n" +
                    "\n" +
                    "    <!-- Content Wrapper -->\n" +
                    "    <div id=\"content-wrapper\" class=\"d-flex flex-column\">\n" +
                    "\n" +
                    "        <!-- Main Content -->\n" +
                    "        <div id=\"content\">\n" +
                    "\n" +
                    "            <!-- Topbar -->\n" +
                    "            <th:block th:replace=\"/layout::topbar\"></th:block>\n" +
                    "            <!-- End of Topbar -->\n" +
                    "\n" +
                    "            <!-- Begin Page Content -->\n" +
                    "            <div class=\"container-fluid\">\n" +
                    "\n" +
                    "                <!-- Page Heading -->\n" +
                    "                <h1 class=\"h3 mb-2 text-gray-800\"" + dtp.table(i) + "s</h1>\n" +
                    "\n" +
                    "                <!-- DataTales Example -->\n" +
                    "                <div class=\"card shadow mb-4\">\n" +
                    "                    <div class=\"card-header py-3\">\n" +
                    "                    <div class=\"card-header py-3\">\n" +
                    "                        <h6 class=\"m-0 font-weight-bold text-primary\">View " + dtp.table(i).toLowerCase() + "s: <span\n";


            question += "                    </div>\n" +
                    "                    <div class=\"card-body\">\n" +
                    "                        <div class=\"table-responsive\">\n" +
                    "                            <table class=\"table table-bordered table-striped\" id=\"dataTable\" width=\"100%\"\n" +
                    "                                   cellspacing=\"0\">\n";
//            for (int j = 1; j < dtp.columnSize(i) - 7; j++) {
//                question += "                                    <th>" + dtp.column(i, j) + "</th>\n";
//
//            }
//            question += "                                </tr>\n" +
//                    "                                    <tr class=\"text-center\">\n";
//            for (int j = 1; j < dtp.columnSize(i) - 7; j++) {
//                question += "                                        <td th:text=\"${" + dtp.table(i).toLowerCase() + "." + dtp.inThuongPK(i, x) + "." + dtp.inThuongCot(i, 0) + "}\"></td>\n";
//
//            }


            for (int j = 1; j < dtp.columnSize(i) - 7; j++) {
                if (dtp.pkSize(i) > 0) {
                    for (int x = 0; x < dtp.pkSize(i); x++) {
                        if (dtp.fk(i).indexOf(dtp.column(i, j)) == -1) {
                            question += "                                    <tr class=\"text-center\">\n" +
                                    "                                        <th>" + dtp.column(i, j) + "</th>\n" +
                                    "                                        <td th:text=\"${" + dtp.table(i).toLowerCase() + "." + dtp.inThuongCot(i, j) + "}\"></td>\n" +
                                    "                                    </tr>";
                        break;
                        } else {
                            if (dtp.column(i, j).equals(dtp.fkName(i, x))) {
//                                question += "                                <td th:text=\"${" + dtp.table(i).toLowerCase() + "." + dtp.pk(i, x).toLowerCase() + "."+dtp.column(i,0)+"}\"></td>\n";
//                                question += "                                <td th:text=\"${" + dtp.table(i).toLowerCase() + "." + dtp.inThuongPK(i, x) + "." + dtp.inThuongCot(i, 0) + "}\"></td>\n";

                                question += "                                    <tr class=\"text-center\">\n" +
                                        "                                        <th>" + dtp.column(i, j) + "</th>\n" +
                                        "                                        <td th:text=\"${" + dtp.table(i).toLowerCase() + "." + dtp.inThuongPK(i, x) + "." + dtp.inThuongCot(i, 0) + "}\"></td>\n" +
                                        "                                    </tr>";
                            break;
                            }

                        }

                    }
                } else {
                    question += "                                    <tr class=\"text-center\">\n" +
                            "                                        <th>" + dtp.column(i, j) + "</th>\n" +
                            "                                        <td th:text=\"${" + dtp.table(i).toLowerCase() + "." + dtp.inThuongCot(i, j) + "}\"></td>\n" +
                            "                                    </tr>";

                }

            }

//                   question+="                                            <td th:text=\"${cauhoi.tencauhoi}\"></td>\n" +
//                   "                                            <td th:text=\"${cauhoi.site}\"></td>\n" ;
//                   question+=
//                   "                                            <td th:text=\"${cauhoi.loai.id}\"></td>\n" +
//                   "                                            <td th:text=\"${cauhoi.survey.id}\"></td>\n" +
//                   "                                        </tr>";

            question += "\n" +
                    "                            </table>\n" +
                    "                        </div>\n" +
                    "                        <div class=\"box-footer mt-2\">\n" +
                    "                            <a href=\"/" + dtp.table(i).toLowerCase() + "s\" class=\"btn btn-secondary btn-flat\">Back to " + dtp.table(i).toLowerCase() + "s list</a>\n" +
                    "                        </div>\n" +
                    "                    </div>\n" +
                    "                </div>\n" +
                    "\n" +
                    "            </div>\n" +
                    "            <!-- /.container-fluid -->\n" +
                    "\n" +
                    "        </div>\n" +
                    "        <!-- End of Main Content -->\n" +
                    "\n" +
                    "        <!-- Footer -->\n" +
                    "        <th:block th:replace=\"/layout::footer\"></th:block>\n" +
                    "        <!-- End of Footer -->\n" +
                    "\n" +
                    "    </div>\n" +
                    "    <!-- End of Content Wrapper -->\n" +
                    "\n" +
                    "</div>\n" +
                    "<!-- End of Page Wrapper -->\n" +
                    "\n" +
                    "<!-- Scroll to Top Button-->\n" +
                    "<th:block th:replace=\"/layout::topbtn\"></th:block>\n" +
                    "\n" +
                    "<!-- Logout Modal-->\n" +
                    "<th:block th:replace=\"/layout::logoutmodal\"></th:block>\n" +
                    "\n" +
                    "<th:block th:replace=\"/layout::jsfiles\"></th:block>\n" +
                    "</body>\n" +
                    "\n" +
                    "</html>";


            Charset charset = Charset.forName("ISO-8859-1");
            try {
                Files.write(path, question.getBytes());
                System.out.println("Views Hacked By Le Duy Phuc");
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

}



