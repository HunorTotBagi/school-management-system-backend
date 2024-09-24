import React from "react";
import { useRouteError } from "react-router-dom";
import { Container, Typography, Paper, Box } from "@mui/material";

const ErrorPage = () => {
  const error = useRouteError();
  console.error(error);

  return (
    <Container maxWidth="sm" sx={{ mt: 4 }}>
      <Paper elevation={3} sx={{ padding: 4, borderRadius: 2 }}>
        <Box textAlign="center">
          <Typography variant="h4" color="error" gutterBottom>
            Error
          </Typography>
          <Typography variant="body1" color="textSecondary">
            {error.statusText || error.message}
          </Typography>
        </Box>
      </Paper>
    </Container>
  );
};

export default ErrorPage;
