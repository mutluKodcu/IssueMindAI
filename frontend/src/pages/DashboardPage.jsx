import React from "react";
import FeedbackCard from "../components/FeedbackCard";

const DashboardPage = () => {
  const mockFeedback = { user: "Ali", comment: "Harika sistem!" };

  return (
    <div>
      <h2>Dashboard</h2>
      <FeedbackCard feedback={mockFeedback} />
    </div>
  );
};

export default DashboardPage;
