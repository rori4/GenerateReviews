import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Generator {
    private List<String> names;
    private List<String> reviews;

    public Generator() {
        this.names = addToLists(Paths.CSVNames);
        this.reviews = addToLists(Paths.CSVReviews);
    }

    public String getRandomName() {
        return this.names.get(randomNumber(0,names.size()-1));
    }

    public String getRandomReview() {
        return this.reviews.get(randomNumber(0,reviews.size()-1));
    }

    public List<String> addToLists(String CSVPath){
        List<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(CSVPath))) {
            String line = "";
            String cvsSplitBy = ",";
            while ((line = br.readLine()) != null) {
                // use comma as separator
                list.add(Arrays.toString(line.split(cvsSplitBy)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int randomNumber(int min,int max){
        int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
        return randomNum;
    }

    public Date randomDate (Date start, Date end){
        return new Date(ThreadLocalRandom.current().nextLong(start.getTime(), end.getTime()));
    }

    static String Spintax(Random rnd, String str)
    {
        String pat = "\\{[^{}]*\\}";
        Pattern ma;
        ma = Pattern.compile(pat);
        Matcher mat = ma.matcher(str);
        while(mat.find())
        {
            String segono = str.substring(mat.start() + 1,mat.end() - 1);
            String[] choies = segono.split("\\|",-1);
            str = str.substring(0, mat.start()) + choies[rnd.nextInt(choies.length)] + str.substring(mat.start()+mat.group().length());
            mat = ma.matcher(str);
        }
        return str;
    }
}
