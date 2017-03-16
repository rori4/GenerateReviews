import com.csvreader.CsvWriter;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Generator gen = new Generator();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        DateFormat formatFile = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");



        List<Review> reviewsList = new ArrayList<>();
        List<String> handles = gen.addToLists(Paths.CSVHandle);

        int reviewsStartAmount = Integer.parseInt(reader.readLine());
        int reviewsEndAmount = Integer.parseInt(reader.readLine());
        int ratingStartValue  = Integer.parseInt(reader.readLine());
        Date startDate = null;

        try {
            startDate = format.parse(reader.readLine());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for (String handle : handles) {

            for (int i = 0; i < gen.randomNumber(reviewsStartAmount,reviewsEndAmount); i++) {
                Review review = new Review(
                        gen.getRandomName(),
                        gen.getRandomReview(),
                        gen.randomNumber(ratingStartValue,5),
                        handle,
                        gen.randomDate(startDate,new Date())
                );
                reviewsList.add(review);
            }


        }
        File file = new File( Paths.output + "\\"+ formatFile.format(new Date()) +"_Statistics.csv");
        if ( !file.exists() )
            file.createNewFile();

        // Use FileWriter constructor that specifies open for appending
        CsvWriter csvOutput = new CsvWriter(new FileWriter(file, true), ',');

        //Create Header for CSV

        for (Review review : reviewsList) {
            csvOutput.write(review.getAuthorName());
            csvOutput.write(review.getReviewText());
            csvOutput.write(Integer.toString(review.getRating()));
            csvOutput.write(review.getHandle());
            csvOutput.write(format.format(review.getDate()));
            csvOutput.endRecord();
        }
        csvOutput.flush();
        csvOutput.close();
        System.out.println("Done!");

    }
}
