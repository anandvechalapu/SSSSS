
Controller:

@RestController
public class GitHubController {
 
    @Autowired
    GitHubService githubService;
 
    @PostMapping("/publish")
    public ResponseEntity<GitHubResponse> publish(@RequestBody GitHubRequest gitHubRequest) {
 
        GitHubResponse response = githubService.publish(gitHubRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

Service:

@Service
public class GitHubService {
 
    @Autowired
    GitHubRepository gitHubRepository;
 
    public GitHubResponse publish(GitHubRequest gitHubRequest) {
        // Create new organization
        Long organizationId = gitHubRepository.createOrganization(gitHubRequest.getOrganizationName());
 
        // Create new repository
        Long repositoryId = gitHubRepository.createRepository(gitHubRequest.getRepositoryName(), organizationId);
 
        // Publish generated code to repository
        gitHubRepository.publishCode(gitHubRequest.getCode(), repositoryId);
 
        // Update request status and link in Java API
        gitHubRepository.updateRequestStatus(gitHubRequest.getRequestId(), repositoryId);
 
        // Update request data in Java API
        gitHubRepository.updateRequestData(gitHubRequest.getRequestId(), gitHubRequest.getInput());
 
        return new GitHubResponse(organizationId, repositoryId);
    }
}

Repository:

@Repository
public class GitHubRepository {
 
    // Create new organization
    public Long createOrganization(String organizationName) {
        // Code to create new organization
    }
 
    // Create new repository
    public Long createRepository(String repositoryName, Long organizationId) {
        // Code to create new repository
    }
 
    // Publish generated code to repository
    public void publishCode(String code, Long repositoryId) {
        // Code to publish generated code
    }
 
    // Update request status and link in Java API
    public void updateRequestStatus(Long requestId, Long repositoryId) {
        // Code to update request status and link
    }
 
    // Update request data in Java API
    public void updateRequestData(Long requestId, String input) {
        // Code to update request data
    }
}