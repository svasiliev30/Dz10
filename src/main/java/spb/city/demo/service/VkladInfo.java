package spb.city.demo.service;

public class VkladInfo {

    private Integer numberVklad;
    private Integer summa;

    /**
     * Конструктор вклада
     */
    public VkladInfo(){

    }

    /**
     * Конструктор для присваивания входящей информации по вкладу
     * @param numberVklad
     * @param summa
     */
    public VkladInfo(Integer numberVklad, Integer summa){
        this.summa = summa;
        this.numberVklad = numberVklad;
    }

    /**
     * Сеттер метод для номера вклада
     * @param numberVklad
     */
    public void setNumberVklad (Integer numberVklad){
        this.numberVklad = numberVklad;
    }

    /**
     * Сеттер метод для суммы вклада
     * @param summa
     */
    public void setSumma(Integer summa){
        this.summa = summa;
    }

    /**
     * Возвращение номера вклада
     * @return
     */
    public Integer getNumberVklad(){ return numberVklad;}

    /**
     * возвращение суммы
     * @return
     */
    public Integer getSumma(){return summa;}

    /**
     * Вывод информации по номеру вклада и суммы
     * @return
     */
    @Override
    public String toString() {
        return "VkladInfo{" +
                "numberVklad = " + numberVklad +
                ", summa = " + summa +
                '}';
    }
}
