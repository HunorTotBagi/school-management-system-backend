import {
  Button,
  TextField,
  Typography,
  Container,
  Box,
  Stack,
} from "@mui/material";
import React, { useRef, useState } from "react";
import { putSubject } from "../../HelperFunctions/SubjectEndpoints";
import { useLocation } from "react-router-dom";

const EditSubject = () => {
  const subjectName = useRef();
  const weeklyFund = useRef();
  const loc = useLocation();

  const [success, setSuccess] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");

  const [isEmptySubjectName, setIsEmptySubjectName] = useState(false);
  const [isEmptyWeeklyFund, setIsEmptyWeeklyFund] = useState(false);

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

  const handleEditSubject = async () => {
    if (validateInput()) {
      try {
        await putSubject(
          localStorage.getItem("username"),
          localStorage.getItem("password"),
          {
            name: subjectName.current.value,
            weeklyFund: Number(weeklyFund.current.value),
          },
          loc.state.subject.id
        );
        setSuccess(true);
        setErrorMessage("");
      } catch (error) {
        console.log("Error:", error);
        setErrorMessage("Error updating the subject. Please try again.");
      }
    }
  };

  return (
    <Container
      className="edit-subject-page"
      maxWidth="sm"
      sx={{ mt: 2, mb: 2 }}
    >
      <Typography variant="h5" component="h1" gutterBottom>
        Edit Subject
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
            defaultValue={loc.state.subject.name}
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
            type="number"
            inputRef={weeklyFund}
            defaultValue={loc.state.subject.weeklyFund}
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
            onClick={handleEditSubject}
            sx={{
              backgroundColor: "#FFB74D",
              "&:hover": { backgroundColor: "#FFC107" },
              mt: 1,
            }}
          >
            Edit Subject
          </Button>
        </Stack>

        {success && (
          <Typography variant="body2" color="success.main" mt={2}>
            ✨🎉 Subject has been updated!🎉✨
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

export default EditSubject;
