function ErrorMessage({ error }) {
  if (!error) return null;

  return (
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
  );
}

export default ErrorMessage;