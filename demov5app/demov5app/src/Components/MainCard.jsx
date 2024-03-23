import { useEffect, useState } from 'react'
import { FetchDeleteMethod, FetchGetMethod, FetchPostMethod, RefreshToken } from '../Services/FetchServices';
import { Link } from 'react-router-dom';

const MainCard = ({ post }) => {
    const [commentArea, setCommentArea] = useState(false)
    const [comment, setComment] = useState("");
    const [allComment, setAllComment] = useState([])
    const [liked, setLiked] = useState(false)
    const [likeCount, setLikeCount] = useState(post.likes.length)
    const [likeId, setLikeId] = useState(null);
    const currUserId = localStorage.getItem("userId");
    const currUser = localStorage.getItem("username");


    useEffect(() => {
        fetchCommentDb();
        post.likes.map((item) => {
            if (item.userId == currUserId) {
                setLiked(true)
                setLikeId(item.id)
            }
        })
    }, [])

    const handleLikeClick = () => {
        setLiked(!liked)
        if (!liked) {
            saveLikeFetch();
            setLikeCount(likeCount + 1)
        } else {
            likeId != null && deleteLikeFetch();
            setLikeCount(likeCount - 1)
        }
    }
    const saveLikeFetch = () => {
         FetchPostMethod("likes",{
            postId: post.id,
            userId: currUserId
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
    }
    
    const deleteLikeFetch = () => {
        FetchDeleteMethod("likes", likeId);
    }
    const fetchCommentDb = async () => {
        const getResponse = await FetchGetMethod(`comments?postId:${post.id}`)
        const newRes = await getResponse.filter((item) => item.postId == post.id)
        await console.log(newRes)
        await setAllComment(newRes)
    }


    const handleEnterKeyPressOnComment = async () => {
        await FetchPostMethod("comments", {
            text: comment,
            postId: post.id,
            userId: currUserId
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
        await setTimeout(() => {
            fetchCommentDb();
        }, 500);
    }
    const HandleDeleteClick = async (id) => {
        await fetch(`http://localhost:8080/comments/${id}`, {
            method: 'DELETE',
            mode: "cors",
            headers: {
                'Accept': 'application/json, text/plain, */*',
                'Content-Type': 'application/json',
                "Authorization": localStorage.getItem("accessToken")

            }
        }).catch((e) => console.log(e))
        await fetchCommentDb();
    }
    return (
        <div className='col-8 mt-3'>
            <div className="card text-align-center" >
                <div className="card-body">
                    <div className='d-flex gap-3 align-items-center'>
                        {/* <img src="..." className="img-thumbnail rounded-circle" alt="..."></img> */}
                        <Link to={`users/${post.userId}`} className='btn btn-danger rounded-circle border-0 m-0'>{post.userName.slice(0, 1)}</Link>
                        <h5 className="card-title fs-5 m-0">{post.userName}</h5>
                    </div>
                    <p className='fs-4 text-bold'>{post.title}</p>
                    <p className='fs-5'>{post.text}</p>
                    <hr />
                    <div className='d-flex justify-content-between px-4'>
                        <div className='d-flex align-items-center justify-content-center'>
                            <i onClick={() => handleLikeClick()} className={`bi bi-suit-heart-fill fs-3 ${liked ? "text-danger" : "text-primary"}`} ></i>
                            <p className='p-0 ps-2 m-0'>{likeCount}</p>
                        </div>
                        <div className='d-flex align-items-center'>
                            <i onClick={() => setCommentArea(!commentArea)} className="bi p-0 m-0 bi-chat-right-text fs-3 text-primary"></i>
                        </div>
                    </div>
                    {commentArea &&
                        <div className='p-4'>
                            <ul className="list-group">
                                {allComment.length > 0 && allComment.map((item) => (
                                    <li key={item.id} className='list-group-item fst-italic text-capitalize d-flex justify-content-between align-items-center'>
                                        <div>{item.text}</div>
                                        {item.userId == currUserId &&
                                            <i onClick={() => HandleDeleteClick(item.id)} className="bi bi-x fs-4"></i>
                                        }
                                    </li>
                                ))}
                            </ul>
                            {
                                currUser ?

                                    <input onChange={(e) => setComment(e.target.value)} onKeyDown={(e) => {
                                        if (e.key == "Enter") {
                                            handleEnterKeyPressOnComment()
                                            setComment("");
                                        }
                                    }}
                                        type="text"
                                        className="form-control mt-4"
                                        placeholder="Username"
                                        aria-label="comment"
                                        value={comment}
                                    /> :
                                    <div className='text-danger py-4'>  Logged In Required For Enter Comments</div>
                            }
                        </div>
                    }
                </div>
            </div>
        </div>
    )
}

export default MainCard