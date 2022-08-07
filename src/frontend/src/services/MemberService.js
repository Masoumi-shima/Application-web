import axios from 'axios'

const BASE_URL = 'http://localhost:8080/api/membres/'

class MemberService {
    getMembers() {
        return axios.get(BASE_URL)
    }
    getAMember(id) {
        const NEW_URL = BASE_URL + id
        return axios.get(NEW_URL)
    }
}

export default new MemberService()