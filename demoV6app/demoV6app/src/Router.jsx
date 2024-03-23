import React from 'react'
import { useRoutes } from 'react-router-dom'
import Login from './Pages/Login'
import Signup from './Pages/Signup'
import MainPage from './Pages/MainPage'


const Router = () => {
  const routes = useRoutes([
    {
        path:"/",
        element: <MainPage />
    },
    {
        path:"/login",
        element: <Login />
    },
    {
        path:"/Signup",
        element: <Signup />
    },
])

  return routes
}

export default Router