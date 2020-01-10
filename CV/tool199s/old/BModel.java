import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

public class BModel {
    public void createModel() throws SQLException, ClassNotFoundException {
        DTP dtp = new DTP();
        dtp.setFullDTP();

        for (int i = 0; i < dtp.tableSize(); i++) {

            Path path = Paths.get(AMain.link + "src/main/java/com/codegym/cms/model/" + dtp.table(i) + ".java");

            String question = "package com.codegym.cms.model;\n" +
                    "\n" +
                    "import org.hibernate.annotations.ColumnDefault;\n" +
                    "\n" +
                    "import javax.persistence.*;\n" +
                    "import java.time.LocalDate;\n" +
                    "import java.util.Set;\n" +
                    "\n" +
                    "@Entity\n" +
                    "@Table(name = \"" + dtp.table(i) + "\")\n" +
                    "public class " + dtp.table(i) + "{\n" +
                    "    @Id\n" +
                    "    @GeneratedValue(strategy= GenerationType.AUTO)\n" +
                    "    private " + dtp.type(i, 0) + " " + dtp.column(i, 0) + ";\n";


            for (int j = 1; j < dtp.columnSize(i); j++) {
                if (dtp.column(i, j).equals("isDeleted")) {
                    question += "@ColumnDefault(\"0\")\n" +
                            "    private int isDeleted;\n";
                } else {
                    if (dtp.pkSize(i) > 0) {
                        for (int x = 0; x < dtp.pkSize(i); x++) {
                            if (dtp.fk(i).indexOf(dtp.column(i, j)) == -1) {
                                question += "private " + dtp.type(i, j) + " " + dtp.column(i, j) + ";\n";
                                break;
                            }
                        }

                    } else {
                        question += "private " + dtp.type(i, j) + " " + dtp.column(i, j) + ";\n";
                    }
                }
            }

            if (dtp.pkSize(i) > 0) {
                for (int j = 0; j < dtp.pkSize(i); j++) {
                    question += "    @ManyToOne\n" +
                            "    @JoinColumn(name = \"" + dtp.fkName(i, j) + "\")\n" +
                            "    private " + dtp.pk(i, j) + " " + dtp.pk(i, j).toLowerCase() + ";\n";
                }

                for (int z = 0; z < dtp.tableSize(); z++) {
                    for (int j = 0; j < dtp.pkSize(z); j++) {
                        if (dtp.pk(z, j).equals(dtp.table(i))) {
                            question += "    @OneToMany(mappedBy = \"" + dtp.table(i).toLowerCase() + "\")\n" +
                                    "    private Set<" + dtp.table(z) + "> " + dtp.table(z).toLowerCase() + ";\n";
                            break;
                        }
                    }
                }
            } else {
                for (int z = 0; z < dtp.tableSize(); z++) {
                    for (int j = 0; j < dtp.pkSize(z); j++) {
                        if (dtp.pk(z, j).equals(dtp.table(i))) {
                            question += "    @OneToMany(mappedBy = \"" + dtp.table(i).toLowerCase() + "\")\n" +
                                    "    private Set<" + dtp.table(z) + "> " + dtp.table(z).toLowerCase() + ";\n";
                            break;
                        }
                    }
                }
            }

            question += "    public " + dtp.table(i) + "() {}\n";
//                    "    public " + dtp.table(i) + "(";

//            for (int j = 1; j < dtp.columnSize(i) - 1; j++) {
//                question += dtp.type(i, j) + " " + dtp.column(i, j) + ", ";
//            }
//
//            question += dtp.type(i, dtp.columnSize(i) - 1) + " " + dtp.column(i, dtp.columnSize(i) - 1) + ") {\n";
//
//            for (int j = 1; j < dtp.columnSize(i) - 1; j++) {
//                question += "this." + dtp.column(i, j) + " = " + dtp.column(i, j) + ";\n";
//            }
//
//            question += "}\n";

            for (int j = 0; j < dtp.columnSize(i); j++) {
                if (dtp.pkSize(i) > 0) {
                    for (int x = 0; x < dtp.pkSize(i); x++) {
                        if (dtp.fk(i).indexOf(dtp.column(i, j)) == -1) {
                            question += " public " + dtp.type(i, j) + " get" + dtp.column(i, j) + "() {\n" +
                                    "        return " + dtp.column(i, j) + ";\n" +
                                    "    }\n" +
                                    "\n" +
                                    "    public void set" + dtp.column(i, j) + "(" + dtp.type(i, j) + " " + dtp.column(i, j) + ") {\n" +
                                    "        this." + dtp.column(i, j) + " = " + dtp.column(i, j) + ";\n" +
                                    "    }\n";
                            break;
                        } else {
                            if (dtp.column(i, j).equals(dtp.fkName(i, x))) {
                                question += "    public " + dtp.pk(i, x) + " get" + dtp.pk(i, x) + "() {\n" +
                                        "        return " + dtp.pk(i, x).toLowerCase() + ";\n" +
                                        "    }\n" +
                                        "\n" +
                                        "    public void set" + dtp.pk(i, x) + "(" + dtp.pk(i, x) + " " + dtp.pk(i, x).toLowerCase() + ") {\n" +
                                        "        this." + dtp.pk(i, x).toLowerCase() + " = " + dtp.pk(i, x).toLowerCase() + ";\n" +
                                        "    }\n";
                            }
                        }
                    }
                } else {
                    question += " public " + dtp.type(i, j) + " get" + dtp.column(i, j) + "() {\n" +
                            "        return " + dtp.column(i, j) + ";\n" +
                            "    }\n" +
                            "\n" +
                            "    public void set" + dtp.column(i, j) + "(" + dtp.type(i, j) + " " + dtp.column(i, j) + ") {\n" +
                            "        this." + dtp.column(i, j) + " = " + dtp.column(i, j) + ";\n" +
                            "    }\n";

                }
            }

            for (int z = 0; z < dtp.tableSize(); z++) {
                for (int x = 0; x < dtp.pkSize(z); x++) {
                    if (dtp.pk(z, x).equals(dtp.table(i))) {
                        question += "    public Set<" + dtp.table(z) + "> get" + dtp.table(z) + "() {\n" +
                                "        return " + dtp.table(z).toLowerCase() + ";\n" +
                                "    }\n" +
                                "\n" +
                                "    public void set" + dtp.table(z) + "(Set<" + dtp.table(z) + "> " + dtp.table(z).toLowerCase() + ") {\n" +
                                "        this." + dtp.table(z).toLowerCase() + " = " + dtp.table(z).toLowerCase() + ";\n" +
                                "    }\n";
                        break;
                    }
                }
            }

            question += "}";

            Charset charset = Charset.forName("ISO-8859-1");
            try {
                Files.write(path, question.getBytes());
                System.out.println("Model created by Dan");
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }


//    public void createModel() throws SQLException, ClassNotFoundException {
//        DTP dtp = new DTP();
//        dtp.setFullDTP();
//
//        for (int i = 0; i < dtp.tableSize(); i++) {
//
//            Path path = Paths.get(AMain.link + "src/main/java/com/codegym/cms/model/" + dtp.table(i) + ".java");
//
//            String question = "package com.codegym.cms.model;\n" +
//                    "\n" +
//                    "import org.hibernate.annotations.ColumnDefault;\n" +
//                    "\n" +
//                    "import javax.persistence.*;\n" +
//                    "import java.time.LocalDate;\n" +
//                    "import java.util.Set;\n" +
//                    "\n" +
//                    "@Entity\n" +
//                    "@Table(name = \"" + dtp.table(i) + "\")\n" +
//                    "public class " + dtp.table(i) + "{\n" +
//                    "    @Id\n" +
//                    "    @GeneratedValue(strategy= GenerationType.AUTO)\n" +
//                    "    private " + dtp.type(i, 0) + " " + dtp.column(i, 0) + ";\n";
//
//
////            int jj =0;
////            for(int j=1;i<dtp.columnSize(i)-7;j++){
////                if(dtp.fk(i).indexOf(dtp.column(i,j))== -1){
////                    question += 1;
////                }
////                else{
////                    question+=2;
////                    jj++;
////
////                }
////
////            }
//
//
//            for (int j = 1; j < dtp.columnSize(i); j++) {
//                if (dtp.column(i, j).equals("isDeleted")) {
//                    question += "@ColumnDefault(\"0\")\n" +
//                            "    private int isDeleted;\n";
//                } else {
//                    if (dtp.pkSize(i) > 0) {
//                        for (int x = 0; x < dtp.pkSize(i); x++) {
////                            if (!dtp.column(i, j).equals(dtp.fkName(i, x))) {
////                                System.out.println("table  " + dtp.table(i));
////                                System.out.println("colum  " + dtp.column(i, j));
////                                System.out.println("fk  " + dtp.fkName(i, x));
////                                System.out.println(dtp.fk(i).indexOf(dtp.column(i,j)));
////                                System.out.println("===================");
////                                question += "private " + dtp.type(i, j) + " " + dtp.column(i, j) + ";\n";
////                            }
//
//                            if (dtp.fk(i).indexOf(dtp.column(i, j)) == -1) {
//                                question += "private " + dtp.type(i, j) + " " + dtp.column(i, j) + ";\n";
//                                break;
//                            }
//                        }
//
//                    } else {
//                        question += "private " + dtp.type(i, j) + " " + dtp.column(i, j) + ";\n";
//                    }
//                }
//            }
//
//            if (dtp.pkSize(i) > 0) {
//                for (int j = 0; j < dtp.pkSize(i); j++) {
//                    question += "    @ManyToOne\n" +
//                            "    @JoinColumn(name = \"" + dtp.fkName(i, j) + "\")\n" +
//                            "    private " + dtp.pk(i, j) + " " + dtp.pk(i, j).toLowerCase() + ";\n";
//                }
//            } else {
//                for (int z = 0; z < dtp.tableSize(); z++) {
//                    for (int j = 0; j < dtp.pkSize(z); j++) {
//                        if (dtp.pk(z, j).equals(dtp.table(i))) {
//                            question += "    @OneToMany(mappedBy = \"" + dtp.table(i).toLowerCase() + "\")\n" +
//                                    "    private Set<" + dtp.table(z) + "> " + dtp.table(z).toLowerCase() + ";\n";
//                            break;
//                        }
//                    }
//                }
//            }
//
//            question += "    public " + dtp.table(i) + "() {}\n";
////                    "    public " + dtp.table(i) + "(";
//
////            for (int j = 1; j < dtp.columnSize(i) - 1; j++) {
////                question += dtp.type(i, j) + " " + dtp.column(i, j) + ", ";
////            }
////
////            question += dtp.type(i, dtp.columnSize(i) - 1) + " " + dtp.column(i, dtp.columnSize(i) - 1) + ") {\n";
////
////            for (int j = 1; j < dtp.columnSize(i) - 1; j++) {
////                question += "this." + dtp.column(i, j) + " = " + dtp.column(i, j) + ";\n";
////            }
////
////            question += "}\n";
//
//            for (int j = 0; j < dtp.columnSize(i); j++) {
//                if (dtp.pkSize(i) > 0) {
//                    for (int x = 0; x < dtp.pkSize(i); x++) {
//                        if (dtp.fk(i).indexOf(dtp.column(i, j)) == -1) {
//                            question += " public " + dtp.type(i, j) + " get" + dtp.column(i, j) + "() {\n" +
//                                    "        return " + dtp.column(i, j) + ";\n" +
//                                    "    }\n" +
//                                    "\n" +
//                                    "    public void set" + dtp.column(i, j) + "(" + dtp.type(i, j) + " " + dtp.column(i, j) + ") {\n" +
//                                    "        this." + dtp.column(i, j) + " = " + dtp.column(i, j) + ";\n" +
//                                    "    }\n";
//                            break;
//                        } else {
//                            if (dtp.column(i, j).equals(dtp.fkName(i, x))) {
//                                question += "    public " + dtp.pk(i, x) + " get" + dtp.pk(i, x) + "() {\n" +
//                                        "        return " + dtp.pk(i, x).toLowerCase() + ";\n" +
//                                        "    }\n" +
//                                        "\n" +
//                                        "    public void set" + dtp.pk(i, x) + "(" + dtp.pk(i, x) + " " + dtp.pk(i, x).toLowerCase() + ") {\n" +
//                                        "        this." + dtp.pk(i, x).toLowerCase() + " = " + dtp.pk(i, x).toLowerCase() + ";\n" +
//                                        "    }\n";
//                            }
//                        }
////                        if (dtp.column(i, j).equals(dtp.fkName(i, x))) {
////                            question += "    public " + dtp.pk(i, x) + " get" + dtp.pk(i, x) + "() {\n" +
////                                    "        return " + dtp.pk(i, x).toLowerCase() + ";\n" +
////                                    "    }\n" +
////                                    "\n" +
////                                    "    public void set" + dtp.pk(i, x) + "(" + dtp.pk(i, x) + " " + dtp.pk(i, x).toLowerCase() + ") {\n" +
////                                    "        this." + dtp.pk(i, x).toLowerCase() + " = " + dtp.pk(i, x).toLowerCase() + ";\n" +
////                                    "    }\n";
////                            break;
////                        } else {
////                            question += " public " + dtp.type(i, j) + " get" + dtp.column(i, j) + "() {\n" +
////                                    "        return " + dtp.column(i, j) + ";\n" +
////                                    "    }\n" +
////                                    "\n" +
////                                    "    public void set" + dtp.column(i, j) + "(" + dtp.type(i, j) + " " + dtp.column(i, j) + ") {\n" +
////                                    "        this." + dtp.column(i, j) + " = " + dtp.column(i, j) + ";\n" +
////                                    "    }\n";
////                        }
//                    }
//                } else {
//                    question += " public " + dtp.type(i, j) + " get" + dtp.column(i, j) + "() {\n" +
//                            "        return " + dtp.column(i, j) + ";\n" +
//                            "    }\n" +
//                            "\n" +
//                            "    public void set" + dtp.column(i, j) + "(" + dtp.type(i, j) + " " + dtp.column(i, j) + ") {\n" +
//                            "        this." + dtp.column(i, j) + " = " + dtp.column(i, j) + ";\n" +
//                            "    }\n";
//
//                }
//            }
//            if (dtp.pkSize(i) == 0) {
//                for (int z = 0; z < dtp.tableSize(); z++) {
//                    for (int x = 0; x < dtp.pkSize(z); x++) {
//                        if (dtp.pk(z, x).equals(dtp.table(i))) {
//                            question += "    public Set<" + dtp.table(z) + "> get" + dtp.table(z) + "() {\n" +
//                                    "        return " + dtp.table(z).toLowerCase() + ";\n" +
//                                    "    }\n" +
//                                    "\n" +
//                                    "    public void set" + dtp.table(z) + "(Set<" + dtp.table(z) + "> " + dtp.table(z).toLowerCase() + ") {\n" +
//                                    "        this." + dtp.table(z).toLowerCase() + " = " + dtp.table(z).toLowerCase() + ";\n" +
//                                    "    }";
//                            break;
//                        }
//                    }
//                }
//            }
//
//            question += "}";
//
//            Charset charset = Charset.forName("ISO-8859-1");
//            try {
//                Files.write(path, question.getBytes());
//                System.out.println("Model created by Dan");
//            } catch (IOException e) {
//                System.out.println(e);
//            }
//        }
//    }


}
