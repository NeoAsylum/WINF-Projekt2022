package Annotations;

public @interface ZuEditieren {
    boolean mussUeberarbeitetWerden() default false;
    String zugewiesen() default "niemandem";
}
