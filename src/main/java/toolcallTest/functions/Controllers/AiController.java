package toolcallTest.functions.Controllers;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import toolcallTest.functions.services.RectangleService;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/ai")
public class AiController {
    private ChatModel chatModel;


    @Autowired
    public void OpenAiRestController(OpenAiChatModel chatModel) {
        this.chatModel = chatModel;
    }
    @GetMapping("/function")
    public Generation functionCalling(@RequestParam(value = "message") String message) {
        // Parse the incoming message or response to determine the function to call
        Set<String> functions = new HashSet<>();
        functions.add("rectangleAreaFunction");
        functions.add("rectanglePerimeterFunction");
        functions.add("disableUserForEnv");
        Prompt prompt = new Prompt(message,
                OpenAiChatOptions.builder()
                        .withFunctions(functions)
                        .build());

        ChatResponse response = chatModel.call(prompt);
        return  response.getResult();
    }

}
