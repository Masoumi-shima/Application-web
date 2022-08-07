import axios from 'axios'

const BASE_URL = 'http://localhost:8080/api/membres'

class MemberService {
    getMembers() {
        return axios.get(BASE_URL)
    }
}

export default new MemberService()