package ca.qc.mtl.mohaila.simplegame

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.FitViewport

class SimpleGame : ApplicationAdapter() {
    companion object {
        const val gameWidth = 1280f
        const val gameHeight = 720f
    }
    private val spriteBatch by lazy {
        SpriteBatch()
    }
    private val logoSprite by lazy {
        val texture = Texture("badlogic.jpg")
        Sprite(texture)
    }
    private val camera by lazy {
        OrthographicCamera()
    }
    private val viewport by lazy {
        FitViewport(gameWidth, gameHeight, camera)
    }
    private var xSpeed = 5f
    private var ySpeed = 5f

    override fun resize(width: Int, height: Int) =
        camera.setToOrtho(false, gameWidth, gameHeight)

    override fun render() {
        with(Gdx.gl) {
            glClearColor(0f, 0f, 0f, 1f)
            glClear(GL20.GL_COLOR_BUFFER_BIT)
        }

        animateLogo()

        camera.update()
        with(spriteBatch) {
            projectionMatrix = camera.combined
            begin()
            draw(logoSprite, logoSprite.x, logoSprite.y)
            end()
        }
    }

    private fun animateLogo() {
        logoSprite.x += xSpeed
        logoSprite.y += ySpeed
        if (logoSprite.x > gameWidth - logoSprite.width) {
            xSpeed = -5f
        } else if (logoSprite.x < 0) {
            xSpeed = 5f
        }

        if (logoSprite.y > gameHeight - logoSprite.height) {
            ySpeed = -5f
        } else if (logoSprite.y < 0) {
            ySpeed = 5f
        }
    }

    override fun dispose() {
        spriteBatch.dispose()
        logoSprite.texture.dispose()
    }
}