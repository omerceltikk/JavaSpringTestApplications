import React, { useEffect, useState } from 'react'
import MainCard from '../Components/MainCard'
import { FetchGetMethod } from '../Services/FetchServices'

const MainPage = () => {
    const [posts, setPosts] = useState([]);
    const getAllPosts = async () => {
        const allPosts = await FetchGetMethod("posts");
        await setPosts(allPosts);
        await console.log(posts)
    }
    useEffect(() => {
        getAllPosts();
    }, [])
    return (
        <div className='bg-primary fill py-5'>
            <div className='container py-5'>
                <div className='row justify-content-center'>
                    {
                        posts && posts?.length != 0 ? posts.map((item) => <MainCard post={item} key={item.id} />) :
                            <div> No Post Committed </div>
                    }
                </div>
            </div>
        </div>
    )
}

export default MainPage