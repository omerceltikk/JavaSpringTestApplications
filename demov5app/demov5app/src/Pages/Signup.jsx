import React from 'react'
import { useFormik } from 'formik';
import { SignupSchema } from '../Schemas';
import { Link, Navigate,useNavigate } from 'react-router-dom';
import { FetchPostMethod, FetchGetMethod } from '../Services/FetchServices';
import { ToastContainer, toast } from 'react-toastify';
import { useState, useEffect } from 'react';
const Signup = () => {
    const navigate = useNavigate();

    const errorToastMessage = (text) => {
        toast.error(text)
    };
    const successToastMessage = (text) => {
        toast.success(text)
    };



    const onSubmit = (values) => {

        if (values.password == values.confirmPassword) {
            fetch(`http://localhost:8080/auth/register`, {
                method: 'POST',
                mode: "cors",
                cache: "no-cache",
                headers: {
                    'Accept': 'application/json, text/plain, */*',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    userName: values.username,
                    password: values.password
                })
            }).then((res) => res.json()).then((res) => {
                successToastMessage(res)
                navigate("/")

            })
                .catch((e) => console.log(e))
        } else {
            errorToastMessage("password did not match")
        }
    }


    const formik = useFormik({
        initialValues: {
            username: "",
            password: "",
            confirmPassword: ""
        },
        validationSchema: SignupSchema, onSubmit
    })

    return (
        <>
            <ToastContainer />
            <div className='bg-primary fill'>
                <div className="container">
                    <div className="row fill justify-content-center align-items-center text-center">
                        <div className="card col-4 text-align-center" >
                            <div className="card-body">
                                <h5 className="card-title">Sign Up</h5>
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
                                    <div className='form-group pb-3'>
                                        <label htmlFor="password2">Confirm Password:</label>
                                        <input
                                            id='password2'
                                            className='form-control border-primary'
                                            type="confirmPassword"
                                            name="confirmPassword"
                                            onChange={formik.handleChange}
                                            onBlur={formik.handleBlur}
                                            value={formik.values.confirmPassword}
                                        />
                                    </div>
                                    <div>
                                        <button type="submit" className='btn btn-primary py-2 px-4' >
                                            Submit
                                        </button>
                                        {formik.errors.password && <div className='text-danger error'>{formik.errors.password}</div>}
                                    </div>
                                    <div className='mt-3'>
                                        <Link to="/login" className='text-primary fst-italic'>Or Login from this link.</Link>
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

export default Signup