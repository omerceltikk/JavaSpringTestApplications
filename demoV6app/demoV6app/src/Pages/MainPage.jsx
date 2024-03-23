import React, { useEffect, useState } from 'react'
import { FetchGetMethod, FetchPostMethod } from '../Services/FetchServices';
const MainPage = () => {
    const [deCrypt, setDeCrypt] = useState([]);
    const [crypt, setCrypt] = useState("");
    const [deCryptedText, setDeCryptedText] = useState("");
    const currUserId = localStorage.getItem("userId");
    // const getAllPosts = async () => {
    //     const allPosts = await FetchGetMethod("posts");
    //     await setPosts(allPosts);
    //     await console.log(posts)
    // }
    // useEffect(() => {
    //     getAllPosts();
    // }, [])
    const fetchCryptDb = async () => {
        const res = await FetchGetMethod("posts");
        setDeCrypt(res);
    }
    useEffect(() => {
        fetchCryptDb();
    }, [])
    const handleClick = async () => {
        await FetchPostMethod("posts", {
            text: crypt,
            userId: currUserId
        }).catch((err) => console.log(err))
        await console.log(crypt)
        await setCrypt("");
        await setTimeout(() => {
            fetchCryptDb();
        }, 500);
    }
    const handleDecryptClick = async (id) => {
        await FetchGetMethod(`posts/${id}`,
        )
            .then((res) => setDeCryptedText(res.cryptText))
            .catch((err) => console.log(err))
    }
    const handleMainDecryptClick = async (decrypt) => {
        await FetchPostMethod("posts/decrypt", {
            text: decrypt
        }).then((res) => res.json()).then((res) => setDeCryptedText(res.text ? res.text : "can not be solved"))
        .catch((err) => console.log(err))
        
    }
    return (
        <div className='bg-primary fill'>
            <div className='container py-5'>
                <div className='justify-content-center pt-5'>
                    <div className="row">
                        <div className="col-6">
                            <div className="card p-3">
                                <div className="card-body">
                                    <h5 className="card-title pb-2">Please Enter Text</h5>
                                    <h6 className="card-subtitle mb-2 text-body-secondary">Card subtitle</h6>
                                    <textarea onChange={(e) => setCrypt(e.target.value)} value={crypt} className="form-control" aria-label="With textarea"></textarea>
                                    <div className='d-flex justify-content-end'>
                                        <div onClick={() => handleMainDecryptClick(crypt)} className='btn btn-primary rounded-3 mt-3 px-5 me-3'>Decrypt</div>
                                        <div onClick={() => handleClick()} className='btn btn-primary rounded-3 mt-3 px-5'>Encrypt</div>
                                    </div>
                                </div>
                            </div>
                            {
                                deCryptedText &&
                                <div className="bg-light p-5 rounded-3 mt-3 text-center">
                                    {
                                        deCryptedText
                                    }
                                </div>
                            }
                        </div>
                        <div className="col-6">
                            <div className="card">
                                <div className="card-body">
                                    <h5 className="card-title">Card title</h5>
                                    <h6 className="card-subtitle mb-2 text-body-secondary">Card subtitle</h6>
                                    <ul className="list-group mt-5">
                                        {
                                            deCrypt && deCrypt?.length > 0 && deCrypt.map((item) => (

                                                <div key={item.textId} className="list-group-item d-flex justify-content-between align-items-center">
                                                    <p className='m-0 p-0 pe-3 col-9'>{item.text}</p>
                                                    <div onClick={() => handleDecryptClick(item.textId, item.text)} className='btn btn-primary rounded-3 m-0 fs-6 col-3'>Decrypt</div>
                                                </div>
                                            ))
                                        }
                                    </ul>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div className="col-6"></div>
                </div>
            </div>
        </div>
    )
}

export default MainPage