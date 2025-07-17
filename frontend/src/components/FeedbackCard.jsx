import React from "react";

const FeedbackCard = ({ feedback }) => (
  <div className="card">
    <h3>{feedback.user}</h3>
    <p>{feedback.comment}</p>
  </div>
);

export default FeedbackCard;
