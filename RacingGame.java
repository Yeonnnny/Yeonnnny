import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Car {

    int speed;
    String name;
    int score;

    Car() {};

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void getCarInfo() {
        System.out.print("스피드는 " + speed + "이고, 이름은 " + name + "입니다.\n");
    }

    public void go() {
        score += speed;
    }

    public void sayScore() {
        System.out.println(name + "의 score : " + score);
    }

}

// 슈퍼카 클래스
class SuperCar extends Car {
    int booster;

    @Override
    public void go() {
        Random random = new Random();
        if (random.nextInt(2) == 1) {
            booster += 1;
            score += speed * 2;
        }else {
            score+=speed;
        }
    }

    @Override
    public void sayScore() {
        System.out.println(name + "의 score : " + score + ", booster : " + booster);
    }
}

public class RacingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1단계 : 참가자 소개 기능

        System.out.print("참가자 수 입력 (2명 이상): ");
        int num = scanner.nextInt();
        System.out.print("\n");

        ArrayList<Car> cars = new ArrayList<>();

        for (int i = 0; i < num; i++) {

            System.out.print(i + 1 + "번째 참가자의 스피드 입력 : ");
            int speed = scanner.nextInt();


            System.out.print(i + 1 + "번째 참가자의 이름 입력 : ");
            String name = scanner.next();


            System.out.print("이 자동차는 슈퍼카인가요? 0 또는 1 입력 : ");
            int isSuperCar = scanner.nextInt();
            System.out.print("\n");


            if (isSuperCar == 1) {
                SuperCar superCar = new SuperCar();
                superCar.setSpeed(speed);
                superCar.setName(name);
                cars.add(superCar);
            } else {
                Car car = new Car();
                car.setSpeed(speed);
                car.setName(name);
                cars.add(car);
            }

        }

        System.out.print("-----경기 참가자 소개-----\n");
        for (Car car : cars) {
            car.getCarInfo();
        }

        // 2단계 : 자동차 경주 규칙

        System.out.println("\n");
        System.out.println("경기를 몇 초 동안 진행할까요?");
        int sec = scanner.nextInt();

        long seed = System.currentTimeMillis();
        Random random = new Random(seed);

        for (int i = 0; i < sec; i++) {
            for (Car car : cars) {
                if (random.nextInt(2) == 1) {
                    car.go();
                }
            }
        }

        System.out.println("\n---최종 결과 발표---");

        for (Car car : cars) {
            car.sayScore();
        }
    }
}
