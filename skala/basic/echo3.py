import requests
import unittest

def get_github_user_info(username):
    url = f"https://api.github.com/users/{username}"
    response = requests.get(url)
    
    # 정상 응답 상태 코드(200) 체크
    if response.status_code == 200:
        user_data = response.json()
        return {
            "login": user_data["login"],
            "name": user_data["name"],
            "company": user_data["company"],
            "location": user_data["location"],
            "public_repos": user_data["public_repos"],
            "followers": user_data["followers"],
            "following": user_data["following"]
        }
    else:
        # 비정상 상태(404 또는 기타 오류)
        return {"error": f"Failed to retrieve data for user '{username}'. Status code: {response.status_code}"}

class TestGitHubAPI(unittest.TestCase):

    def test_valid_user(self):
        valid_username = "octocat"  # GitHub에서 존재하는 사용자
        valid_result = get_github_user_info(valid_username)
        
        self.assertIn("login", valid_result, f"Expected login field in result for {valid_username}")
        self.assertEqual(valid_result["login"], "octocat", "Expected login to be 'octocat'")
    
    def test_invalid_user(self):
        invalid_username = "nonexistentuser"  # 존재하지 않는 사용자
        invalid_result = get_github_user_info(invalid_username)
        
        self.assertIn("error", invalid_result, f"Expected error message for {invalid_username}")
        self.assertTrue("Failed to retrieve data" in invalid_result["error"], "Expected failure message for nonexistent user")

if __name__ == "__main__":
    unittest.main()
