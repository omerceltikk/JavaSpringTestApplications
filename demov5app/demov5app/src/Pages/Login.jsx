import React, { useEffect, useState } from 'react'
import { useFormik } from 'formik';
import { LoginSchema } from '../Schemas';
import { Link, Navigate,useNavigate } from 'react-router-dom';
import { FetchGetMethod, FetchPostMethod } from '../Services/FetchServices';
import { ToastContainer, toast } from 'react-toastify';

const Login = () => {
    const navigate = useNavigate();
    const errorToastMessage = (text) => {
        toast.error(text)
    };

    const onSubmit = async(values) => {
        fetch(`http://localhost:8080/auth/login`,{
            method: 'POST',
            mode: "cors",
            cache:"no-cache", 
            headers: {
              'Accept': 'application/json, text/plain, */*',
              'Content-Type': 'application/json'
            },
            body:JSON.stringify({
                userName: values.username,
                password: values.password
            })
        }).then((res) => res.json()).then((res) => {
            console.log(res)
            localStorage.setItem("accessToken", res.accessToken);
            localStorage.setItem("refreshToken", res.refreshToken);
            localStorage.setItem("userId", res.userId);
            localStorage.setItem("username", values.username);
            navigate("/")
        })
        .catch((e) =>  console.log(e))
        ;
    }

    const formik = useFormik({
        initialValues: {
            username: "",
            password: "",
        },
        validationSchema: LoginSchema, onSubmit
    })

    return (
        <>
            <ToastContainer />
            <div className='bg-primary fill'>
                <div className="container">
                    <div className="row fill justify-content-center align-items-center text-center">
                        <div className="card col-4 text-align-center" >
                            <div className="card-body">
                                <h5 className="card-title">Login</h5>
                                <form onSubmit={formik.handleSubmit}>
                                    <div className='form-group py-3'>
                                        <label htmlFor="username1">User Name:</label>
                                        <input
                                            id='username1'
                                            className='form-control border-primary'
                                            type="username"
                                            name="username"
                                            onChange={formik.handleChange}
                                            onBlur={formik.handleBlur}
                                            value={formik.values.username}
                                        />
                                    </div>
                                    <div className='form-group pb-3'>
                                        <label htmlFor="password1">Password:</label>
                                        <input
                                            id='password1'
                                            className='form-control border-primary'
                                            type="password"
                                            name="password"
                                            onChange={formik.handleChange}
                                            onBlur={formik.handleBlur}
                                            value={formik.values.password}
                                        />
                                    </div>
                                    <div>
                                        <button type="submit" className='btn btn-primary py-2 px-4' >
                                            Submit
                                        </button>
                                        {formik.errors.password && <div className='text-danger error'>{formik.errors.password}</div>}
                                    </div>
                                    <div className='mt-3'>
                                        <Link to="/signup" className='text-primary fst-italic'>Or Sign Up from this link.</Link>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}

export default Login