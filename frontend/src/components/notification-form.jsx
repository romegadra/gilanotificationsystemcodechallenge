import { useState } from "react";

function NotificationForm({ onSubmit }) {
  const [category, setCategory] = useState("SPORTS");
  const [message, setMessage] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();

    onSubmit({
      category,
      message,
    });

    setMessage("");
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>Category:</label>
        <select value={category} onChange={(e) => setCategory(e.target.value)}>
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
  );
}

export default NotificationForm;