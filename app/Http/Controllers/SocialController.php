<?php

namespace App\Http\Controllers;

use App\Models\Report;
use App\Models\Comment;
use App\Models\Like;
use App\Models\User;
use Illuminate\Http\Request;

class SocialController extends Controller
{
    // Toggle Like on a Report
    public function toggleLike(Request $request, $id)
    {
        $report = Report::findOrFail($id);
        $user = $request->user();

        // Check if already liked
        $existingLike = Like::where('user_id', $user->id)
                            ->where('report_id', $report->id)
                            ->first();

        if ($existingLike) {
            $existingLike->delete();
            return response()->json(['message' => 'Unliked', 'liked' => false]);
        } else {
            Like::create([
                'user_id' => $user->id,
                'report_id' => $report->id
            ]);
            return response()->json(['message' => 'Liked', 'liked' => true]);
        }
    }

    // Add a Comment to a Report
    public function addComment(Request $request, $id)
    {
        $request->validate([
            'content' => 'required|string|max:1000',
        ]);

        $report = Report::findOrFail($id);

        $comment = Comment::create([
            'user_id' => $request->user()->id,
            'report_id' => $report->id,
            'content' => $request->content,
        ]);

        return response()->json([
            'message' => 'Comment added',
            'data' => $comment->load('user') // Return comment with user info
        ], 201);
    }

    // Get Leaderboard (Users with most reports)
    public function leaderboard()
    {
        // Fetch top 10 users ordered by report count
        $leaders = User::withCount('reports')
            ->orderBy('reports_count', 'desc')
            ->take(10)
            ->get();

        return response()->json($leaders);
    }
}