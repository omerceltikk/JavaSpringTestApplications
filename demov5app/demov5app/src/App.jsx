import { useState, useEffect } from 'react'
import axios from "axios";
import Router from './Router';
import Navbar from './Components/Navbar';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import Post from './Components/Post';
import { Link } from 'react-router-dom';
function App() {
  // const [count, setCount] = useState(0)
  // const [user, setUser] = useState([]);

  // useEffect(() => {
  //   fetch("http://localhost:8080/users",{
  //     method: 'get',
      
  //   }).then(res => console.log(res)).then((res) => console.log(res))
  // },[])
  return (
    <>
      <ToastContainer />
      <Navbar/>
      <Router/>
      <div className='position-fixed bottom-0 end-0 bg-light py-3 px-4 m-4 rounded-circle btn'>
      <Link to={"/send_post"} > 
      <i className="bi bi-plus fs-3"></i>
      </Link>
      </div>
    </>
  )
}

export default App
