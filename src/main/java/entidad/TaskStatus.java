package entidad;

// Los enum son utiles cuando tienes un grupo fijo de valores que quieres usar en tu aplicación,
// ejemplo: dias de semana, estados de un proceso (este caso)
// esto despues se usa llamando a TaskStatus con un metodo para que pase el string asociado al valor
// enum.
public enum TaskStatus {
    TO_DO("To do"),
    IN_PROGRESS("In Progess"),
    DONE("Done");

    private final String titleCaseStatus;

    private TaskStatus(String titleCaseStatus) {
        this.titleCaseStatus = titleCaseStatus;
    }

    private String getTitleCaseStatus() {
        return titleCaseStatus;
    }

}
