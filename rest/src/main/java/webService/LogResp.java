package webService;

import javax.ws.rs.NameBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//- метааннотация для создания новых аннотаций, которые можно использовать как перехватчики
// интерцепторы. Например можно сделать логгер.
@NameBinding //идею подсмотрел в доке тут https://jakarta.ee/specifications/restful-ws/3.0/jakarta-restful-ws-spec-3.0.html#Name_Binding
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface LogResp {
}
