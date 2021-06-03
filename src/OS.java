import java.io.*;
import java.util.Hashtable;

public class OS {

    static Hashtable<String, String> variables = new Hashtable<>();

    public static void main(String[] args) throws Exception {
        execute("src/Program 1.txt");
        execute("src/Program 2.txt");
        execute("src/Program 3.txt");
    }

    private static void execute(String programPath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(programPath));
        while (br.ready()) {
            interpret(br.readLine());
        }
    }

    private static void print(String x) {
        System.out.println(variables.getOrDefault(x, x));
    }


    private static void assign(String variable, String value) {
        variables.put(variable, value);
    }

    private static void add(String x, String y) {
        int i1 = Integer.parseInt(variables.get(x));
        int i2 = Integer.parseInt(variables.get(y));
        int res= i1 + i2;
        assign(x,""+res);
    }

    private static void writeFile(String fileName, String data) throws IOException {
        File file = new File("src/"+ fileName +".txt");
        if (file.createNewFile())
            System.out.println("File has been created.");
         else
            System.out.println("File already exists.");

        String filePath = "src/"+ fileName +".txt";
        FileWriter fw = new FileWriter(filePath);
        fw.write(data);
        fw.close();
    }

    private static String readFile(String fileName) throws IOException {
        String filePath = "src/"+ fileName +".txt";
        FileReader fr = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fr);
        String data = "";
        while (br.ready())
            data += br.readLine();
        return data;
    }

    public static void interpret(String s) throws IOException {

        String[] words = s.split(" ");
        switch (words[0]) {
            case ("assign"): {
                String result ;
                switch (words[2]) {
                    case ("input"): {
                        result = input();
                        break;
                    }
                    case ("readFile"): {
                        String fileName = words[3];
                        result = readFile(fileName);
                        break;
                    }
                    default:result=words[2];
                }
                assign(words[1], result);
                break;
            }
            case ("writeFile"): {
                writeFile(words[1], words[2]);
                break;
            }
            case ("print"): {
                print(words[1]);
                break;
            }
            case ("readFile"): {
                readFile(words[1]);
                break;
            }
            case ("add"): {
                add(words[1], words[2]);
                break;
            }
        }


    }

    private static String input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }

}
