package sotosuna.encora.dev.todo_app;

import java.time.LocalDateTime;
import java.util.UUID;

public class Todo {
    private UUID id;
    private String text;
    private String priority;
    private LocalDateTime dueDate;
    private boolean completed;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;  // Use LocalDateTime to include time information

    public Todo() {
        this.id = UUID.randomUUID();
    }

    // Getters and setters for all attributes, including the new completedAt attribute

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
}