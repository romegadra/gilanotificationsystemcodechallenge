import axios from "axios";

const API_URL = "http://localhost:8080/api/notifications";

export const sendNotification = (payload) => {
  return axios.post(API_URL, payload);
};

export const getNotificationLogs = () => {
  return axios.get(`${API_URL}/logs`);
};