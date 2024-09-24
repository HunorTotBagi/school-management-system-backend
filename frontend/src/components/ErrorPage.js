import React from "react";
import { useRouteError } from "react-router-dom";

const ErrorPage = () => {
  const error = useRouteError();
  console.error(error);

  return (
    <div>
      <p className="error-page-message">Error</p>
      <p className="error-page-message">{error.statusText || error.message}</p>
    </div>
  );
};

export default ErrorPage;
