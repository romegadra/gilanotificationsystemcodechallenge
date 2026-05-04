function SuccessMessage({ response }) {
  if (!response) return null;

  return (
    <div style={{ marginTop: "20px", color: "green" }}>
      <h3>Success</h3>
      <p>Total: {response.totalNotifications}</p>
      <p>Success: {response.successfulNotifications}</p>
      <p>Failed: {response.failedNotifications}</p>
    </div>
  );
}

export default SuccessMessage;