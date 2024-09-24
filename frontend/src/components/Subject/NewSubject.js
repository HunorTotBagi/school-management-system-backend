import {
  Button,
  TextField,
  Typography,
  Container,
  Box,
  Stack,
} from "@mui/material";
import React, { useRef, useState } from "react";
import { postSubject } from "../../HelperFunctions/SubjectEndpoints";
import { useNavigate } from "react-router-dom";

const NewSubject = () => {
  const subjectName = useRef();
  const weeklyFund = useRef();

  const [successMessage, setSuccessMessage] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  const [isEmptySubjectName, setIsEmptySubjectName] = useState(false);
  const [isEmptyWeeklyFund, setIsEmptyWeeklyFund] = useState(false);

  const navigation = useNavigate();

  const validateInput = () => {
    let valid = true;

    if (subjectName.current.value.length === 0) {
      setIsEmptySubjectName(true);
      valid = false;
    }

    if (
      weeklyFund.current.value.length === 0 ||
      isNaN(weeklyFund.current.value) ||
      Number(weeklyFund.current.value) <= 0
    ) {
      setIsEmptyWeeklyFund(true);
      valid = false;
    }

    return valid;
  };

  const handleAddSubject = async () => {
    if (validateInput()) {
      try {
        const name = subjectName.current.value;
        await postSubject(
          localStorage.getItem("username"),
          localStorage.getItem("password"),
          {
            name: name,
            weeklyFund: Number(weeklyFund.current.value),
          }
        );
        setSuccessMessage(
          `✨🎉Successfully added "${name}" to the list of subjects!🎉✨`
        );
        subjectName.current.value = "";
        weeklyFund.current.value = "";
        setErrorMessage("");

        setTimeout(() => {
          navigation(-1);
        }, 2000);
      } catch (error) {
        console.log("Error:", error);
        setErrorMessage("Error adding the subject. Please try again.");
      }
    }
  };

  return (
    <Container className="new-subject-page" maxWidth="sm" sx={{ mt: 2, mb: 2 }}>
      <Typography variant="h5" component="h1" gutterBottom>
        Add New Subject
      </Typography>

      <Box className="form-container" component="form" mt={2}>
        <Stack spacing={1}>
          <TextField
            label="Subject Name"
            required
            fullWidth
            color="primary"
            variant="outlined"
            inputRef={subjectName}
            error={isEmptySubjectName}
            helperText={isEmptySubjectName && "Subject name must not be blank!"}
            onFocus={() => setIsEmptySubjectName(false)}
            sx={{ borderRadius: 1 }}
          />

          <TextField
            label="Weekly Fund"
            required
            fullWidth
            color="primary"
            variant="outlined"
            inputRef={weeklyFund}
            type="number"
            error={isEmptyWeeklyFund}
            helperText={
              isEmptyWeeklyFund && "Weekly fund must be a positive number!"
            }
            onFocus={() => setIsEmptyWeeklyFund(false)}
            sx={{ borderRadius: 1 }}
          />

          <Button
            variant="contained"
            color="primary"
            onClick={handleAddSubject}
            sx={{
              backgroundColor: "#FFB74D",
              "&:hover": { backgroundColor: "#FFC107" },
              mt: 1,
            }}
          >
            Add Subject
          </Button>
        </Stack>

        {successMessage && (
          <Typography variant="body2" color="success.main" mt={2}>
            {successMessage}
          </Typography>
        )}

        {errorMessage && (
          <Typography variant="body2" color="error.main" mt={1}>
            {errorMessage}
          </Typography>
        )}
      </Box>
    </Container>
  );
};

export default NewSubject;
