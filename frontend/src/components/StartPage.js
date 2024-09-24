import {
  Button,
  Stack,
  TextField,
  Typography,
  Box,
  Container,
  Paper,
} from "@mui/material";
import React, { useRef, useState, useContext } from "react";
import { getLogin } from "../HelperFunctions/LoginEndpoints.js";
import Storage from "../Store/Storage";

const StartPage = () => {
  const storage = useContext(Storage);
  const usernameReference = useRef();
  const passwordReference = useRef();
  const [err, setErr] = useState("");

  const [isEmptyUsernameInput, setIsEmptyUsernameInput] = useState(false);
  const [isEmptyPasswordInput, setIsEmptyPasswordInput] = useState(false);

  const validateInput = () => {
    let valid = true;

    if (usernameReference.current.value.length === 0) {
      setIsEmptyUsernameInput(true);
      valid = false;
    }

    if (passwordReference.current.value.length === 0) {
      setIsEmptyPasswordInput(true);
      valid = false;
    }

    return valid;
  };

  const handleLogin = async () => {
    if (validateInput()) {
      try {
        const response = await getLogin(
          usernameReference.current.value,
          passwordReference.current.value
        );

        localStorage.setItem("username", usernameReference.current.value);
        localStorage.setItem("password", passwordReference.current.value);
        storage.setRole(response.data.role.name);
        storage.setIsLoggedIn(true);
      } catch (error) {
        console.log("Login failed!");
        setErr("Wrong credentials");
      }
    }
  };

  return (
    <Container maxWidth="sm" sx={{ mt: 4 }}>
      <Paper elevation={3} sx={{ padding: 4, borderRadius: 2 }}>
        {storage.isLoggedIn ? (
          <>
            <Typography variant="h4" align="center" gutterBottom>
              Welcome to the School Management System
            </Typography>
            <Box textAlign="center">
              <Typography variant="h6">
                Welcome back {localStorage.getItem("username")}
              </Typography>
            </Box>
          </>
        ) : (
          <Box>
            <Typography variant="h5" align="center" gutterBottom>
              Please log in to continue
            </Typography>

            {err && (
              <Typography color="error" align="center" sx={{ mb: 2 }}>
                {err}
              </Typography>
            )}

            <Stack direction="column" spacing={2} sx={{ mt: 2 }}>
              <TextField
                label="Username"
                variant="outlined"
                color="primary"
                required
                inputRef={usernameReference}
                error={isEmptyUsernameInput}
                helperText={
                  isEmptyUsernameInput ? "Username must not be blank!" : ""
                }
                onFocus={() => setIsEmptyUsernameInput(false)}
                sx={{ borderRadius: 1 }}
              />
              <TextField
                label="Password"
                variant="outlined"
                color="primary"
                type="password"
                required
                inputRef={passwordReference}
                error={isEmptyPasswordInput}
                helperText={
                  isEmptyPasswordInput ? "Password must not be blank!" : ""
                }
                onFocus={() => setIsEmptyPasswordInput(false)}
                sx={{ borderRadius: 1 }}
              />
              <Button
                variant="contained"
                onClick={handleLogin}
                sx={{
                  backgroundColor: "#FFB74D",
                  "&:hover": { backgroundColor: "#FFC107" },
                }}
              >
                Login
              </Button>
            </Stack>
          </Box>
        )}
      </Paper>
    </Container>
  );
};

export default StartPage;
