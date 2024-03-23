import React from 'react'
import { useFormik } from 'formik';
import { PostSchema } from '../Schemas';
import { Link, Navigate, useNavigate } from 'react-router-dom';
import { FetchPostMethod, FetchGetMethod } from '../Services/FetchServices';

const Post = () => {
    const currUserId = localStorage.getItem("userId")
    const navigate = useNavigate();
    const onSubmit = async(values) => {
            await FetchPostMethod("posts", {
                userId: currUserId,
                title: values.title,
                text: values.text
            }).then((res) => {
                if(!res.ok){
                    RefreshToken().then((res) => {
                        if(!res.ok){
                            localStorage.clear();
                        }else{
                            res.json();
                        }
                    }).then((result) => {
                        if(result != undefined)
                        localStorage.setItem("accessToken", result.accessToken);
                        handleEnterKeyPressOnComment();
                    })
                }
            }).catch((err) => console.log(err))
            await navigate("/");
            
    }
    
    const formik = useFormik({
        initialValues: {
            title: "",
            text: "",
        },
        validationSchema: PostSchema, onSubmit
    })



    return (
        <>
            <div className='bg-primary fill'>
                <div className="container">
                    <div className="row fill justify-content-center align-items-center text-center">
                        <div className="card col-4 text-align-center" >
                            <div className="card-body">
                                <h5 className="card-title">Post</h5>
                                <form onSubmit={formik.handleSubmit}>
                                    <div className='form-group py-3'>
                                        <label htmlFor="title">Title:</label>
                                        <input
                                            id='title'
                                            className='form-control border-primary'
                                            type="title"
                                            name="title"
                                            onChange={formik.handleChange}
                                            onBlur={formik.handleBlur}
                                            value={formik.values.title}
                                        />
                                    </div>
                                    <div className='form-group pb-3'>
                                        <label htmlFor="text">text:</label>
                                        <input
                                            id='text'
                                            className='form-control border-primary'
                                            type="text"
                                            name="text"
                                            onChange={formik.handleChange}
                                            onBlur={formik.handleBlur}
                                            value={formik.values.text}
                                        />
                                    </div>
                                    <div>
                                        <button type="submit" className='btn btn-primary py-2 px-4' >
                                            Submit
                                        </button>
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

export default Post