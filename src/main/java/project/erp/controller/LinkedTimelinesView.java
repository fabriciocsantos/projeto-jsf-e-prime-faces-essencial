package project.erp.controller;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.component.timeline.TimelineUpdater;
import org.primefaces.event.timeline.TimelineSelectEvent;
import org.primefaces.model.timeline.TimelineEvent;
import org.primefaces.model.timeline.TimelineModel;

@Named("linkedTimelinesView")
@ViewScoped
public class LinkedTimelinesView implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TimelineModel<Task, ?> modelFirst;  // model of the first timeline
    private TimelineModel<String, ?> modelSecond; // model of the second timeline
    private boolean aSelected;         // flag if the project A is selected (for test of select() call on the 2. model)

    private LocalDateTime start = LocalDate.of(2023, 8, 22).atStartOfDay();
    private LocalDateTime end = LocalDate.of(2023, 9, 4).atStartOfDay();

    @PostConstruct
    public void init() {
        createFirstTimeline();
        createSecondTimeline();
    }

    private void createFirstTimeline() {
        modelFirst = new TimelineModel<>();

        modelFirst.add(TimelineEvent.<Task>builder()
                .data(new Task("E-mail do chefe", "src/main/webapp/resourses/algaworks/icons/mail.png", false))
                .startDate(LocalDateTime.of(2023, 8, 22, 17, 30))
                .build());
        modelFirst.add(TimelineEvent.<Task>builder()
                .data(new Task("Ligue de volta para o meu chefe", "src/main/webapp/resourses/algaworks/icons/callback.png", false))
                .startDate(LocalDateTime.of(2023, 8, 23, 23, 0))
                .build());
        modelFirst.add(TimelineEvent.<Task>builder()
                .data(new Task("Viajar para espanha", "src/main/webapp/resourses/algaworks/icons/location.png", false))
                .startDate(LocalDateTime.of(2023, 8, 24, 21, 45))
                .build());
        modelFirst.add(TimelineEvent.<Task>builder()
                .data(new Task("Fazer tarefa de casa", "src/main/webapp/resourses/algaworks/icons/homework.png", true))
                .startDate(LocalDate.of(2023, 8, 26))
                .endDate(LocalDate.of(2023, 9, 2))
                .build());
        modelFirst.add(TimelineEvent.<Task>builder()
                .data(new Task("Criar memorando", "src/main/webapp/resourses/algaworks/icons/memo.png", false))
                .startDate(LocalDate.of(2023, 8, 28))
                .build());
        modelFirst.add(TimelineEvent.<Task>builder()
                .data(new Task("Criar relatório", "src/main/webapp/resourses/algaworks/icons/report.png", true))
                .startDate(LocalDate.of(2023, 8, 31))
                .endDate(LocalDate.of(2023, 9, 3))
                .build());

        // duplicate events with 6 month-shift to check for potential daylight-saving-issues
        new ArrayList<TimelineEvent<Task>>(modelFirst.getEvents()).forEach(e -> {
            modelFirst.add(TimelineEvent.<Task>builder()
                    .data(e.getData())
                    .startDate(e.getStartDate().minusMonths(6))
                    .endDate(e.getEndDate() == null ? null : e.getEndDate().minusMonths(6))
                    .build());
        });
    }

    private void createSecondTimeline() {
        modelSecond = new TimelineModel<>();

        modelSecond.add(TimelineEvent.<String>builder()
                .data("Projeto A")
                .startDate(LocalDate.of(2023, 8, 23))
                .endDate(LocalDate.of(2023, 8, 30))
                .build());
        modelSecond.add(TimelineEvent.<String>builder()
                .data("Projeto B")
                .startDate(LocalDate.of(2023, 8, 27))
                .endDate(LocalDate.of(2023, 8, 31))
                .build());
    }

    public void onSelect(TimelineSelectEvent<?> e) {
        // get a thread-safe TimelineUpdater instance for the second timeline
        TimelineUpdater timelineUpdater = TimelineUpdater.getCurrentInstance(":form:timelineSecond");

        if (aSelected) {
            // select project B visually (index in the event's list is 1)
            timelineUpdater.select("projectB");
        }
        else {
            // select project A visually (index in the event's list is 0)
            timelineUpdater.select("projectA");
        }

        aSelected = !aSelected;

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Projeto selecionado: " + (aSelected ? "A" : "B"), null);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public TimelineModel<Task, ?> getModelFirst() {
        return modelFirst;
    }

    public TimelineModel<String, ?> getModelSecond() {
        return modelSecond;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public class Task implements Serializable {

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private final String title;
        private final String imagePath;
        private final boolean period;

        public Task(String title, String imagePath, boolean period) {
            this.title = title;
            this.imagePath = imagePath;
            this.period = period;
        }

        public String getTitle() {
            return title;
        }

        public String getImagePath() {
            return imagePath;
        }

        public boolean isPeriod() {
            return period;
        }
    }

}