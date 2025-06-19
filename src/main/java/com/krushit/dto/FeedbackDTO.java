package com.krushit.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = FeedbackDTO.FeedbackDTOBuilder.class)
public class FeedbackDTO {
    private final int rating;
    private final String comment;

    private FeedbackDTO(FeedbackDTOBuilder builder) {
        this.rating = builder.rating;
        this.comment = builder.comment;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class FeedbackDTOBuilder {
        private int rating;
        private String comment;

        public FeedbackDTOBuilder setRating(int rating) {
            this.rating = rating;
            return this;
        }

        public FeedbackDTOBuilder setComment(String comment) {
            this.comment = comment;
            return this;
        }

        public FeedbackDTO build() {
            return new FeedbackDTO(this);
        }
    }
}
