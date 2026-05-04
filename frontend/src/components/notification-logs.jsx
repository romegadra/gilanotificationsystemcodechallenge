function NotificationLogs({ logs }) {
  return (
    <>
      <h2>Logs</h2>

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
    </>
  );
}

export default NotificationLogs;