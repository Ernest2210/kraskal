import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try(BufferedReader fileReader = new BufferedReader(new FileReader("data.txt"));
            FileWriter answersFile = new FileWriter("answers.txt");
            FileWriter outputFile = new FileWriter("output.txt")){
            String[] SystemInfo = fileReader.readLine().split(":"); // прочтение первого заголовка DataSet
            int[] info = new int[2];
            info[0] = Integer.parseInt(SystemInfo[1]);
            info[1] = Integer.parseInt(SystemInfo[2]);
            ArrayList<Integer[]> edges = new ArrayList<>();
            int countOfDatasetReaded = 0;
            while (fileReader.ready()){
                String line = fileReader.readLine();
                if(line.contains("DataSet") || line.contains("EOD")){
                    long m = System.currentTimeMillis();
                    ArrayList<Integer[]> answer = Kraskal.getMinimalOstov(info[1], edges);
                    long time = System.currentTimeMillis() - m;
                    answersFile.write("Dataset\n");
                    for (int i = 0; i < answer.size(); i++) {
                        StringBuilder st = new StringBuilder();
                        st.append(answer.get(i)[0] + " " + answer.get(i)[1] + " " + answer.get(i)[2] + "\n");
                        answersFile.write(st.toString());
                    }
                    outputFile.write(info[0] + ":" + Kraskal.countOfInterations + ":" + time + "\n");
                    if(line.contains("DataSet")){
                        Kraskal.countOfInterations = 0;
                        SystemInfo = line.split(":");
                        info[0] = Integer.parseInt(SystemInfo[1]);
                        info[1] = Integer.parseInt(SystemInfo[2]);
                        edges = new ArrayList<>();
                    }
                    System.out.println(countOfDatasetReaded);
                    countOfDatasetReaded++;
                }else{
                    String[] data = line.split(":");
                    Integer[] numbers = new Integer[3];
                    for (int i = 0; i < data.length ; i++) {
                        numbers[i] = Integer.parseInt(data[i]);
                    }
                    edges.add(numbers);
                }
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

//        }
    }
}
