import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

public class BView {
    public void createView() throws SQLException, ClassNotFoundException {
        DTP dtp = new DTP();
        dtp.setFullDTP();

        for (int i = 0; i < dtp.tableSize(); i++) {
            if (dtp.pkSize(i) == 0) {
                for (int z = 0; z < dtp.tableSize(); z++) {
                    for (int x = 0; x < dtp.pkSize(z); x++) {
                        if (dtp.pk(z, x).equals(dtp.table(i))) {

                            Path path = Paths.get(AMain.link + "src/main/webapp/WEB-INF/views/" + dtp.table(i).toLowerCase() + "/view.html");

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
                                    "                <h1 class=\"h3 mb-2 text-gray-800\">" + dtp.table(i) + "</h1>\n" +
                                    "\n" +
                                    "                <!-- DataTales Example -->\n" +
                                    "                <div class=\"card shadow mb-4\">\n" +
                                    "                    <div class=\"card-header py-3\">\n" +
                                    "                        <h6 class=\"m-0 font-weight-bold text-primary\">View " + dtp.table(i).toLowerCase() + ":</h6>\n" +
                                    "                    </div>\n" +
                                    "                    <div class=\"card-body\">\n" +
                                    "                        <div class=\"table-responsive\">\n" +
                                    "                            <table class=\"table table-bordered table-striped\" id=\"dataTable\" width=\"100%\"\n" +
                                    "                                   cellspacing=\"0\">\n" +
                                    "                                <tr class=\"text-center\">\n";
                            for (int j = 1; j < dtp.columnSize(z) - 7; j++) {
                                if (!dtp.column(z, j).equals(dtp.fkName(z, x))) {
                                    question += "                                    <th>" + dtp.column(z, j) + "</th>\n";
                                }
                            }
                            question +=
                                    "                                </tr>\n" +
                                            "                                <th:block th:each=\"" + dtp.table(z).toLowerCase() + " : ${" + dtp.table(z).toLowerCase() + "s}\">\n" +
                                            "                                    <tr class=\"text-center\">\n";
                            for (int j = 1; j < dtp.columnSize(z) - 7; j++) {
                                if (!dtp.column(z, j).equals(dtp.fkName(z, x))) {
                                    question += "                                        <td th:text=\"${" + dtp.table(z).toLowerCase() + "." + dtp.column(z, j) + "}\"></td>\n";
                                }
                            }
                            question +=
                                    "                                    </tr>\n" +
                                            "                                </th:block>\n" +
                                            "                            </table>\n" +
                                            "                        </div>\n" +
                                            "                        <div class=\"box-footer mt-2\">\n" +
                                            "                            <a href=\"/" + dtp.table(i).toLowerCase() + "s\" class=\"btn btn-secondary btn-flat\">Back to " + dtp.table(i).toLowerCase() + " list</a>\n" +
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
                                System.out.println("View created by Dan");
                            } catch (IOException e) {
                                System.out.println(e);
                            }
                        }
                    }
                }
            }


        }
    }
}
