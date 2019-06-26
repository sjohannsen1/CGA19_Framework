#version 330 core

//input from vertex shader
in struct VertexData
{
//3.1.3 Farbanpassung
    vec3 normale;
    vec3 position;
    vec2 tc;
    vec3 pos;
    vec3 toLight;
    vec3 toCamera;
} vertexData;
//Textures

uniform sampler2D texDiff;
uniform sampler2D texSpec;
uniform sampler2D texEmit;
uniform float shininess;
uniform vec3 lightColor;
uniform vec3 viewPos;

//fragment shader output
out vec4 color;


void main(){


    vec3 norm= normalize(vertexData.normale);
    vec3 lightDir= normalize(vertexData.toLight-vertexData.toCamera);

    float cosa=max(0.0f,dot(norm, lightDir));
    vec3 DiffuseTerm = texture(texDiff, vertexData.tc).xyz * lightColor;
    vec3 diffuse=cosa*lightColor;
    color = vec4( DiffuseTerm * cosa, 1.0);
    color += vec4(texture(texEmit, vertexData.tc).xyz, 0.0);

    vec3 viewDir = normalize(vertexData.toCamera);
    vec3 R = normalize(reflect(-lightDir,norm));
    float cosBeta = max(0.0, dot(R,viewDir));
    float cosBetak = pow(cosBeta, shininess);

    vec3 SpecularTerm = texture(texSpec, vertexData.tc).xyz * lightColor;
    color += vec4(SpecularTerm * cosBetak, 0.0);

/*
    float specularStrength=0.5f;

    vec3 reflectDir = reflect(-lightDir, norm);
    float spec = pow(max(dot(viewDir, reflectDir), 0.0), 32);
    vec3 specular = specularStrength * spec * lightColor;

    vec3 result= ambient+diffuse+specular;
    vec4 result2=(texture(texDiff, vertexData.tc)*vec4(lightColor, 1.0f))+texture(texSpec,vertexData.tc);
    //color=vec4(result, 1.0f)*texture(texEmit, vertexData.tc);
    color=result2*texture(texEmit, vertexData.tc);

*/


}
