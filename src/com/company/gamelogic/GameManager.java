package com.company.gamelogic;

import com.company.entities.*;
import com.company.entities.Car;
import com.company.util.ConsoleHelper;
import com.company.util.SettingsHelper;
import com.company.view.BattleField;
import java.util.Map;
import java.util.HashMap;


public class GameManager {

    private Player player1, player2;
    private BattleField battleField;

    private int battleFieldCountRows;
    private int battleFieldCountColumns;
    private static int trackLength;

    private static Map<Integer, Car> cars = new HashMap<Integer, Car>();


    public GameManager() {
        SettingsHelper.loadFromFile();

        battleFieldCountRows = SettingsHelper.getIntValue("battleFieldCountRows");//кол-во строк поля
        battleFieldCountColumns = SettingsHelper.getIntValue("battleFieldCountColumns");//кол-во столбцов поля

        trackLength = SettingsHelper.getIntValue("trackLength");//Длина трассы в метрах
    }

    //region method to display the loop on the screen

    //ввод игроков и взаимодействие на поле
    public void init() {
        battleField = new BattleField(battleFieldCountRows, battleFieldCountColumns);
        battleField.clear();//очистить поле

        String player1Name = ConsoleHelper.inputString("Введите имя игрока 1: ");
        player1 = new Player(player1Name);

        String player2Name = ConsoleHelper.inputString("Введите имя игрока 2: ");
        player2 = new Player(player2Name);


    }

    public void gameLoop() throws Exception {
        while (true) {
            battleField.drawField();//вывод поля на экран
            break;
        }
    }
    //endregion


    //Метод сбора списка гонщиков, участвующих в заездах
    private static void setCars() {

        Car put = cars.put(1, new Car(1, "Toyota", "A4", 6, 250, "Petr"));
        Car put1 = cars.put(2, new Car(2, "Mercedes", "Benz", 7, 310, "Max"));
    }

    //Метод, обновляющий текущее состояние автомобиля в заезде: скорость и пройденную дистанцию
    private static void refresh(Car car, int time) {
        int maxSpeed = Math.round(car.getMaxSpeed() / 3);      //Узнаем максимальную скорость автомобиля в м/с и округлим
        int curSpeed = Math.round(car.getVelocity() * time);   //Вычислим текущую скорость автомобиля в м/с

        //Если вычисленная текущая скорость выше максимальной, то текущую делам равной максимальной
        if (curSpeed > maxSpeed) {
            curSpeed = maxSpeed;
        }

        //Обновляем пройденное расстояние
        car.setRunDistance(car.getRunDistance() + curSpeed > trackLength ? trackLength : (int) (car.getRunDistance() + curSpeed));
        //Обновляем текущую скорость
        car.setCurrentSpeed(Math.round(curSpeed * 3));
    }

    //Метод производящий вычисления заездов
    public void calculationOfRaces() throws InterruptedException {
        HashMap<String, Car> results = new HashMap<String, Car>();

        setCars();//Получим список гонщиков, участвующих в заезде

        int runNum = 1;//Номер заезда
        for (int i = 1; i < cars.size(); i++) {//обходим список участников
            Car left = new Car(cars.get(i+1));//устанавливаем первого гонщика, i++ что бы обходить список парами
            Car right = new Car(cars.get(i));//устанавливаем последнего гонщика

            //метод,который останавливает операцию на ... миллисекунд
            System.out.println("Гонка " + runNum + ": " + left.getDriver() + " на " + left.getManufacturer() + " " + left.getModel() + " против " + right.getDriver() + " на " + right.getManufacturer() + " " + right.getModel());
            Thread.sleep(3000);
            System.out.println("На старт!");
            Thread.sleep(1000);
            System.out.println("Внимание!");
            Thread.sleep(1000);
            System.out.println("Марш!");

            boolean run = true;//заезд в процессе
            int seconds = 0;//секунд с момента старта
            while (run) {
                System.out.println("Гонка " + runNum + ": "
                        + left.getDriver() + " на " + left.getManufacturer() + " " + left.getModel() +
                        " против "
                        + right.getDriver() + " на " + right.getManufacturer() + " " + right.getModel());

                System.out.println(left.getDriver() + ": " + left.getRunDistance() + " метров, " + left.getCurrentSpeed() + " км/ч");
                System.out.println(right.getDriver() + ": " + right.getRunDistance() + " метров, " + right.getCurrentSpeed() + " км/ч");

                Thread.sleep(1000);
                seconds++;

//                System.out.println(results.put(1,2));

                //для первого гонщика
                if (left.getRunTime() == 0)//если гонщик ещё не финишировал
                    refresh(left, seconds);//обновим его информацию

                //для следующего гонщика
                if (right.getRunTime() == 0)
                    refresh(right, seconds);

                if (left.getRunTime() > 0 && right.getRunTime() > 0)//если оба гонщика финишировали
                    run = false;//завершим заезд


                //для первого гонщика
                if (left.getRunDistance() >= trackLength && left.getRunTime() == 0) {//если гонщик финишировал
                    left.setRunTime(seconds);//установим его время заезда
                    results.put(cars.size() - results.size(), left);//добавим в список результатов
                }

                //для следующего гонщика
                if (right.getRunDistance() >= trackLength && right.getRunTime() == 0) {
                    right.setRunTime(seconds);
                    results.put(cars.size() - results.size(), right);
                }
            }

            runNum++;//обновим номер заезда
        }

        for (Map.Entry<Integer, Car> set : results.entrySet())//обойдём список результатов
            //вывод на экран
            System.out.println(set.getValue().getRunTime() + " секунд: " + set.getValue().getDriver() + " на " + set.getValue().getManufacturer() + " " + set.getValue().getModel());

    }

}