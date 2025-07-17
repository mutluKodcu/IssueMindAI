import axios from "axios";

export const getFeedback = async () => {
  const response = await axios.get("/api/feedback");
  return response.data;
};
