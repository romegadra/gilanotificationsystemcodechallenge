import { useEffect, useState } from "react";
import { getNotificationLogs, sendNotification } from "./api/notificationApi";
import NotificationForm from "./components/notification-form";
import SuccessMessage from "./components/success-message";
import ErrorMessage from "./components/error-message";
import NotificationLogs from "./components/notification-logs";


function App() {
  const [logs, setLogs] = useState([]);
  const [response, setResponse] = useState(null);
  const [error, setError] = useState(null);

  const fetchLogs = async () => {
    const res = await getNotificationLogs();
    setLogs(res.data);
  };

  useEffect(() => {
    fetchLogs();
  }, []);

  const handleSendNotification = async (payload) => {
    setError(null);
    setResponse(null);

    try {
      const res = await sendNotification(payload);
      setResponse(res.data);
      await fetchLogs();
    } catch (err) {
      setError(err.response?.data || { message: "Unexpected error" });
    }
  };

  return (
    <div style={{ padding: "20px", fontFamily: "Arial" }}>
      <h1>Notification System</h1>

      <NotificationForm onSubmit={handleSendNotification} />

      <SuccessMessage response={response} />
      <ErrorMessage error={error} />

      <NotificationLogs logs={logs} />
    </div>
  );
}

export default App;