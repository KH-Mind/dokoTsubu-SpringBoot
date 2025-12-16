package com.example.dokotsubu.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostMutterForm {

    @NotBlank(message = "つぶやき内容を入力してください")
    // ここで文字数を変更できます。max = 140 で140文字制限。
    @Size(min = 1, max = 140, message = "つぶやきは1文字以上140文字以内で入力してください")
    private String text;
}
