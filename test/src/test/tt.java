package test;

import java.util.Calendar;
import java.util.Date;

public class tt {

	public static void main(String[] args) {
		if(isDayTime(new Date())){//����
			System.out.println("����");
		}else{//ҹ��
			System.out.println("ҹ��");
			
		};
	}
	public static boolean isDayTime(Date nowTime) {
		Date beginTime = new Date();
		if (nowTime.after(getHourDate(7)) && nowTime.before(getHourDate(24))) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
     * ��ȡ����ʱ�����
     * @param date
     * @return ... HH:00:00.000
     */
    public static Date getHourDate(Integer hour) {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.HOUR_OF_DAY,hour);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);
            return c.getTime();
    }

}
