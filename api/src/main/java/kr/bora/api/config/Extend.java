package kr.bora.api.config;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Map;

interface SuperInterface{

    Map<Object,Object> getName(Class<? extends Myname.Base> param);
}

interface SubInterface extends SuperInterface{
    String getId(Class<? extends Myname> param);
}

class Myname{
    @Getter
    @Setter
    public static class Base{
        String id;

        public Base(String id) {
            this.id = id;
        }
    }
    @Getter
    @Setter
    public static class StaticMyName extends Base{

        String name;

        public StaticMyName(String id, String name) {
            super(id);
            this.name = name;
        }
    }
}

class Getting implements SubInterface{

    @Override
    public Map<Object,Object> getName(Class<? extends Myname.Base> param) {

        return null;
    }

    @Override
    public String getId(Class<? extends Myname> param) {
        return null;
    }
}
public class Extend {


    public static void main(String[] args) throws ClassNotFoundException {
        Getting extend = new Getting();
        Class<Myname.StaticMyName> bookClass = Myname.StaticMyName.class;
        Myname.StaticMyName staticMyName = new Myname.StaticMyName("myId", "haesol");

        Class<? extends Myname.StaticMyName> aClass = staticMyName.getClass();
        Class<?> bookClass3 = Class.forName("kr.bora.api.config.Extend");

        Arrays.stream(bookClass.getDeclaredFields()).forEach(f -> {
            try {
                f.setAccessible(true);  //  true로 지정해야 private 접근가능
                System.out.printf("%s %s \n", f, f.get(staticMyName));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });


    }
}