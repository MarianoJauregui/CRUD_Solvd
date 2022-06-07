package threadExample;

import entities.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MultiThreading {

    private static final Logger LOGGER = LogManager.getLogger(MultiThreading.class);

    public static void main(String[] args) {
        Student student1 = new Student(1L, "Mariano1", "Jauregui", "asd@asd.com");
        Student student2 = new Student(2L, "Mariano2", "Jauregui", "asd@asd.com");
        Student student3 = new Student(3L, "Mariano3", "Jauregui", "asd@asd.com");
        Student student4 = new Student(4L, "Mariano4", "Jauregui", "asd@asd.com");
        Student student5 = new Student(5L, "Mariano5", "Jauregui", "asd@asd.com");

        ThreadPoolExecutor tpe = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        tpe.submit(
                () -> {LOGGER.info("Running threads");
        });
        tpe.execute((Runnable) student1);
        tpe.execute((Runnable) student2);
        tpe.execute((Runnable) student3);
        tpe.execute((Runnable) student4);
        tpe.execute((Runnable) student5);

    }
}
