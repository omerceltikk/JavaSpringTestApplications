import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
const Navbar = () => {
    const [user,setUser] = useState(null)
    
    useEffect(() => {
        const currUserId = localStorage.getItem("userId")
        setUser({
            userId:currUserId
        })
    },[])

    const handleClick = () => {
        localStorage.clear("username");
        localStorage.clear("userId");
        setUser(null)
    }
    return (
        <div className='position-fixed w-100 bg-light'>
            <div className='container'>
                <nav className="navbar navbar-light justify-content-between bg-light">
                    <Link to="/" className="navbar-brand">Navbar</Link>
                    {user?.userId ?
                        <div>
                            <Link to={`/users/${user.userId}`}><button className="btn btn-outline-primary my-2 " type="submit"> Profile</button> </Link>
                            <Link to={"/"}><button className="btn btn-outline-primary my-2 " onClick={() => handleClick()} type="submit"> Logout</button> </Link>
                        </div>
                        :
                        <div>
                            <Link to="/login"><button className="btn btn-outline-primary my-2 " type="submit"> Login</button> </Link>
                            <Link to="/signup"><button className="btn btn-outline-primary my-2 " type="submit"> Sign Up</button> </Link>
                        </div>
                    }
                </nav>
            </div>
        </div>
    )
}

export default Navbar