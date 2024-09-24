import React from 'react';

const Storage = React.createContext({
  isLoggedIn: false,
  setIsLoggedIn: () => {},
  role: '',
  setRole: () => {},
  isComicSans: false,
  setIsComicSans: () => {},
});

export default Storage;
