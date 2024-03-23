import { useState } from 'react'
import Router from './Router'
import Navbar from "./Pages/Navbar"
function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <Navbar/>
      <Router/>
    </>
  )
}

export default App
