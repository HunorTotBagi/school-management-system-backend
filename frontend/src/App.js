import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import { getSubjects } from './HelperFunctions/SubjectEndpoints';
import { getParents} from './HelperFunctions/ParentEndpoints';
import { useState } from 'react';
import { createTheme, ThemeProvider } from '@mui/material';
import './Css/App.css';

import ErrorPage from './components/ErrorPage';
import MainPage from './components/MainPage';
import StartPage from './components/StartPage';

import EditSubject from './components/Subject/EditSubject';
import NewSubject from './components/Subject/NewSubject';
import Subjects from './components/Subject/Subjects';

import EditParent from './components/Parent/EditParent';
import NewParent from './components/Parent/NewParent';
import Parents from './components/Parent/Parents';

import Storage from './Store/Storage';

const loadData = async (request, item) => {
  let url = new URL(request.url);
  let q = url.searchParams.get('q');

  let response;

  const username = localStorage.getItem('username');
  const password = localStorage.getItem('password');

  if (item === 'subjects') {
    response = await getSubjects(username, password);
  }

  if (item === 'parents') {
    response = await getParents(username, password);
  }

  if (!q || q === '') {
    return response.data;
  } else {
    return response.data.filter((v) => {
      let w = q.toLowerCase();
      let r;

      if (item === 'subjects') {
        r = v.name.toLowerCase().includes(w);
      }

      if (item === 'parents') {
        r =
          v.firstName.toLowerCase().includes(w) ||
          v.lastName.toLowerCase().includes(w);
      }

      return r;
    });
  }
};

const router = createBrowserRouter([
  {
    path: '/',
    element: <MainPage />,
    errorElement: <ErrorPage />,

    children: [
      {
        index: true,
        element: <StartPage />,
      },
      {
        path: 'subjects',
        element: <Subjects />,
        loader: ({ request }) => {
          return loadData(request, 'subjects');
        },
      },
      {
        path: 'parents',
        element: <Parents />,
        loader: ({ request }) => {
          return loadData(request, 'parents');
        },
      },
      {
        path: 'newsubject',
        element: <NewSubject />,
      },
      {
        path: 'newparent',
        element: <NewParent />,
      },
      {
        path: 'editsubject',
        element: <EditSubject />,
      },
      {
        path: 'editparent',
        element: <EditParent />,
      },
    ],
  },
]);

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [isComicSans, setIsComicSans] = useState(false);
  const [role, setRole] = useState('');

  const theme = createTheme({
    typography: {
      fontFamily: isComicSans
        ? 'cursive'
        : 'Verdana, Geneva, Tahoma, sans-serif',
    },
  });

  return (
    <ThemeProvider theme={theme}>
      <Storage.Provider
        value={{
          isLoggedIn: isLoggedIn,
          setIsLoggedIn: setIsLoggedIn,
          role: role,
          setRole: setRole,
          isComicSans: isComicSans,
          setIsComicSans: setIsComicSans,
        }}
      >
        <RouterProvider router={router} />;
      </Storage.Provider>
    </ThemeProvider>
  );
}

export default App;
