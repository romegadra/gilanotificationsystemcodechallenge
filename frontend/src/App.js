import React, { useEffect, useState } from "react";
import axios from "axios";

const API = "http://localhost:8080/api/notifications";

function App() {
  const [category, setCategory] = useState("SPORTS");
  const [message, setMessage] = useState("");
  const [logs, setLogs] = useState([]);
  const [response, setResponse] = useState(null);
  const [error, setError] = useState(null);

  const fetchLogs = async () => {
    try {
      const res = await axios.get(`${API}/logs`);
      setLogs(res.data);
    } catch (err) {
      console.error(err);
    }
  };

  useEffect(() => {
    fetchLogs();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(null);
    setResponse(null);

    try {
      const res = await axios.post(API, {
        category,
        message,
      });

      setResponse(res.data);
      setMessage("");
      fetchLogs();
    } catch (err) {
      if (err.response) {
        setError(err.response.data);
      } else {
        setError({ message: "Unexpected error" });
      }
    }
  };

  return (
    <div style={{ padding: "20px", fontFamily: "Arial" }}>
      <h1>Notification System</h1>

      {/* FORM */}
      <form onSubmit={handleSubmit}>
        <div>
          <label>Category:</label>
          <select
            value={category}
            onChange={(e) => setCategory(e.target.value)}
          >
            <option value="SPORTS">Sports</option>
            <option value="FINANCE">Finance</option>
            <option value="MOVIES">Movies</option>
          </select>
        </div>

        <div>
          <label>Message:</label>
          <br />
          <textarea
            value={message}
            onChange={(e) => setMessage(e.target.value)}
            rows="4"
            cols="50"
          />
        </div>

        <button type="submit">Send Notification</button>
      </form>

      {/* RESPONSE */}
      {response && (
        <div style={{ marginTop: "20px", color: "green" }}>
          <h3>Success</h3>
          <p>Total: {response.totalNotifications}</p>
          <p>Success: {response.successfulNotifications}</p>
          <p>Failed: {response.failedNotifications}</p>
        </div>
      )}

      {/* ERROR */}
      {error && (
        <div style={{ marginTop: "20px", color: "red" }}>
          <h3>Error</h3>
          <p>{error.message}</p>
          {error.validationErrors &&
            Object.entries(error.validationErrors).map(([key, value]) => (
              <p key={key}>
                {key}: {value}
              </p>
            ))}
        </div>
      )}

      {/* LOGS */}
      <h2 style={{ marginTop: "40px" }}>Logs</h2>
      <table border="1" cellPadding="5">
        <thead>
        <tr>
          <th>ID</th>
          <th>Category</th>
          <th>Message</th>
          <th>Channel</th>
          <th>User</th>
          <th>Status</th>
          <th>Date</th>
        </tr>
        </thead>
        <tbody>
        {logs.map((log) => (
          <tr key={log.id}>
            <td>{log.id}</td>
            <td>{log.messageCategory}</td>
            <td>{log.messageBody}</td>
            <td>{log.notificationChannel}</td>
            <td>{log.userName}</td>
            <td>{log.status}</td>
            <td>{log.createdAt}</td>
          </tr>
        ))}
        </tbody>
      </table>
    </div>
  );
}

export default App;