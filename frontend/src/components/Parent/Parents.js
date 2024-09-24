import React, { useContext, useState, useEffect } from "react";
import { useFetcher, useLoaderData, useNavigate } from "react-router-dom";
import Storage from "../../Store/Storage";
import Parent from "./Parent";
import { Button, Typography, TextField, Container, Box } from "@mui/material";

const Parents = () => {
  const storage = useContext(Storage);
  const fetcher = useFetcher();
  const [q, setQ] = useState("");
  const [parents, setParents] = useState(useLoaderData());
  const navigate = useNavigate();

  useEffect(() => {
    if (fetcher.data) {
      setParents(fetcher.data);
    }
  }, [fetcher.data]);

  if (!storage.isLoggedIn) {
    return (
      <Container>
        <Typography variant="h6">
          You are not allowed to see this page.
        </Typography>
      </Container>
    );
  }

  return (
    <Container maxWidth="lg" sx={{ mt: 2, mb: 2 }}>
      <Typography variant="h4" component="h1" gutterBottom>
        Parents
      </Typography>

      {storage.role === "ROLE_ADMIN" && (
        <Box mb={2}>
          <Button
            variant="contained"
            color="primary"
            onClick={() => navigate("/newParent")}
            sx={{
              backgroundColor: "#FFB74D",
              "&:hover": { backgroundColor: "#FFC107" },
            }}
          >
            Add Parent
          </Button>
        </Box>
      )}

      <Box mb={3}>
        <TextField
          label="Search..."
          variant="outlined"
          fullWidth
          value={q}
          onChange={(e) => {
            setQ(e.target.value);
            fetcher.load(`?q=${encodeURIComponent(e.target.value)}`);
          }}
          sx={{ borderRadius: 1 }}
        />
      </Box>

      <Box
        className="parents-container"
        display="flex"
        flexWrap="wrap"
        justifyContent="center"
        mt={2}
      >
        {parents.length > 0 ? (
          parents.map((p) => (
            <Box
              key={p.id}
              sx={{
                border: "1px solid #ccc",
                borderRadius: "8px",
                padding: "16px",
                margin: "8px",
                flex: "1 0 calc(45% - 16px)",
                maxWidth: "calc(45% - 16px)",
                boxShadow: "0 2px 4px rgba(0,0,0,0.1)",
              }}
            >
              <Parent parent={p} fetcherLoad={fetcher} />
            </Box>
          ))
        ) : (
          <Typography variant="body1">No parents found</Typography>
        )}
      </Box>
    </Container>
  );
};

export default Parents;
